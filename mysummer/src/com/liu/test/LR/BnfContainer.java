package com.liu.test.LR;

import java.util.*;

/**
 * @author shidacaizi
 * @date 2020/5/23 15:52
 */
public class BnfContainer {
    private static final int MASK = 0X80000000;
    private static final int DECODE = 0X7fffffff;
    private static final String separationCharacter = " ";
    public int startIndex = 0;
    int[] preArr;
    private HashMap<String, Integer> NTMap;
    private HashMap<String, Integer> TMap;
    private ArrayList<String> T;
    private ArrayList<NTNode> production;
    private int eof, epsilon;
    private Set<Integer>[] First;
    private int[][] Action;
    private int[][] Goto;
    private List<Set<String>> CC;

    public BnfContainer() {
        NTMap = new HashMap<String, Integer>();
        TMap = new HashMap<String, Integer>();
        T = new ArrayList<String>();
        production = new ArrayList<NTNode>();
        this.addT("eof");
        this.addT("ε");
        eof = this.getTSerialNumber("eof");
        epsilon = this.getTSerialNumber("ε");
    }

    public void setStart(String name) {
        this.addNT(name);
        this.startIndex = this.NTMap.get(name);
    }

    public void addNT(String name) {
        if (name.isEmpty()) {
            System.out.println("终结符不可为空");
            System.exit(-1);
        }
        if (!NTMap.containsKey(name)) {
            NTNode node = new NTNode(name);
            NTMap.put(name, production.size());
            production.add(node);
        }
    }

    public void addT(String name) {
        if (!this.TMap.containsKey(name)) {
            this.TMap.put(name, T.size());
            this.T.add(name);
        }
    }

    private int getTSerialNumber(String name) {
        this.notFindTWarning(name);
        return this.TMap.get(name) | MASK;
    }

    private int getNTSerialNumber(String name) {
        this.notFindNTWarning(name);
        return this.NTMap.get(name);
    }

    public int creatNewExper(String name) {
        this.notFindNTWarning(name);
        NTNode ntn = this.production.get(this.NTMap.get(name));
        return ntn.addExpr();
    }

    public void addExpeElement(String name, int idx, boolean isNt, String addElement) {
        NTNode ntn = this.production.get(this.NTMap.get(name));
        if (isNt) {
            this.notFindNTWarning(name);
            this.notFindNTWarning(addElement);
            ntn.addExprElement(idx, this.getNTSerialNumber(addElement));
        } else {
            this.addT(addElement);
            ntn.addExprElement(idx, this.getTSerialNumber(addElement));
        }
    }

    public void addExpeElement(String name, boolean isNt, String addElement) {
        NTNode ntn = this.production.get(this.NTMap.get(name));
        if (isNt) {
            this.notFindNTWarning(name);
            this.notFindNTWarning(addElement);
            ntn.addExprElement(this.getNTSerialNumber(addElement));
        } else {
            this.addT(addElement);
            ntn.addExprElement(this.getTSerialNumber(addElement));
        }
    }

    private void notFindNTWarning(String name) {
        if (!this.NTMap.containsKey(name)) {
            System.out.println("错误的非终结符" + name + "!");
            System.exit(-1);
        }
    }

    private void notFindTWarning(String name) {
        if (!this.TMap.containsKey(name)) {
            System.out.println("错误的终结符" + name + "!");
            System.exit(-1);
        }
    }

    public void printBNF() {
        System.out.println("开始非终结符为 : " + this.production.get(startIndex).name);
        for (NTNode ntn : this.production) {
            ntn.printNTNode();
        }
        System.out.println("First集 : ");
        int count = 0;
        for (Set<Integer> s : First) {
            System.out.println("第" + count + "个非终结符" + this.production.get(count).name);
            for (Integer i : s) {
                this.printSymbol(i);
            }
            System.out.println();
            count++;
        }
        System.out.println("一共有 " + this.CC.size() + " 种状态");
        for (Set<String> s : this.CC) {
            this.printCCSet(s);
        }
    }

    private void printCCSet(Set<String> s) {
        for (String item : s) {
            this.printItem(item);
        }
        System.out.println();
    }

    private void printItem(String item) {
        String[] strs = item.split(separationCharacter);
        int productionIdx = Integer.parseInt(strs[0]);
        int exprIdx = Integer.parseInt(strs[1]);
        int placeholder = Integer.parseInt(strs[2]);
        int prospectiveSymbol = Integer.parseInt(strs[3]);
        NTNode ntn = this.production.get(productionIdx);
        System.out.print("[" + ntn.name + "-->");
        List<Integer> list = ntn.expr.get(exprIdx);
        for (int i = 0; i < list.size(); i++) {
            if (i == placeholder) {
                System.out.print("·");
            }
            this.printSymbol(list.get(i));
            System.out.print(" ");
        }
        if (list.size() == placeholder) {
            System.out.print("·");
        }
        System.out.print(",");
        this.printSymbol(prospectiveSymbol);
        System.out.print("]\t");
    }

    private void printSymbol(int sym) {
        if (this.isT(sym)) {
            System.out.print(this.T.get(sym & DECODE));
        } else {
            System.out.print(this.production.get(sym).name);
        }
    }

    private void FIRSTAllSymbol() {
        First = new Set[this.production.size()];
        for (int i = First.length - 1; i >= 0; i--) {
            FIRST(i);
        }
        return;
    }

    private void FIRST(int idx) {
        if (First[idx] != null) {
            return;
        }
        First[idx] = new HashSet<Integer>();
        List<List<Integer>> next = this.production.get(idx).expr;
        for (List<Integer> list : next) {
            int val = list.get(0);
            if (this.isT(val)) {
                First[idx].add(val);
            } else {
                this.FIRST(val);
                First[idx].addAll(First[val]);
            }
        }
    }

    private boolean isT(int val) {
        return (val & MASK) == MASK;
    }

    private void closure(Set<String> s) {
        int lastSize = -1;
        while (lastSize != s.size()) {
            lastSize = s.size();
            Set<String> hashset = new HashSet<String>();
            for (String item : s) {
                String[] strs = item.split(separationCharacter);
                int productionIdx = Integer.parseInt(strs[0]);
                int exprIdx = Integer.parseInt(strs[1]);
                int placeholder = Integer.parseInt(strs[2]);
                int prospectiveSymbol = Integer.parseInt(strs[3]);
                List<Integer> temp = this.production.get(productionIdx).expr.get(exprIdx);
                if (placeholder < temp.size() && !this.isT(temp.get(placeholder))) {
                    int cIdx = temp.get(placeholder);
                    Set<Integer> set = this.FIRSTNextStr(temp, placeholder + 1, prospectiveSymbol);
                    List<List<Integer>> expr = this.production.get(cIdx).expr;
                    for (int i = 0; i < expr.size(); i++) {
                        for (Integer val : set) {
                            String res = cIdx + separationCharacter + i + separationCharacter + 0 + separationCharacter + val;
                            hashset.add(res);
                        }
                    }
                }
            }
            s.addAll(hashset);
        }
        return;
    }

    private Set<String> go(Set<String> s, int x) {
        Set<String> res = new HashSet<String>();
        for (String item : s) {
            String[] strs = item.split(separationCharacter);
            int productionIdx = Integer.parseInt(strs[0]);
            int exprIdx = Integer.parseInt(strs[1]);
            int placeholder = Integer.parseInt(strs[2]);
            int prospectiveSymbol = Integer.parseInt(strs[3]);
            List<Integer> temp = this.production.get(productionIdx).expr.get(exprIdx);
            String str = "";
            if (placeholder + 1 <= temp.size() && temp.get(placeholder) == x) {
                str = productionIdx + separationCharacter + exprIdx + separationCharacter + (placeholder + 1) + separationCharacter + prospectiveSymbol;
                res.add(str);
            }
        }
        this.closure(res);
        return res;
    }

    private Set<Integer> FIRSTNextStr(List<Integer> expr, int idx, int prospectiveSymbol) {
        Set<Integer> res = new HashSet<Integer>();
        if (idx >= expr.size()) {
            res.add(prospectiveSymbol);
            return res;
        }
        if (this.isT(expr.get(idx))) {
            res.add(expr.get(idx));
            return res;
        }
        res.addAll(First[expr.get(idx)]);
        if (res.contains(this.epsilon)) {
            res.remove(this.epsilon);
            res.addAll(this.FIRSTNextStr(expr, idx + 1, prospectiveSymbol));
        }
        return res;
    }

    private void initPreArr() {
        this.preArr = new int[this.production.size()];
        if (this.preArr.length > 0) {
            this.preArr[0] = this.production.get(0).expr.size();
            for (int i = 1; i < this.preArr.length; i++) {
                this.preArr[i] = this.preArr[i - 1] + this.production.get(i).expr.size();
            }
        }
    }

    public void toLRTable() {
        this.initPreArr();
        this.FIRSTAllSymbol();
        Set<String> CC0 = new HashSet<String>();
        List<List<Integer>> expr = this.production.get(startIndex).expr;
        for (int i = 0; i < expr.size(); i++) {
            CC0.add(this.startIndex + separationCharacter + i + separationCharacter + 0 + separationCharacter + this.eof);
        }
        this.closure(CC0);
        CC = new ArrayList<Set<String>>();
        CC.add(CC0);
        int begin = 0;
        int lastSize = -1;
        List<Node> res = new ArrayList<Node>();
        int endState = -1;
        while (lastSize != CC.size()) {
            lastSize = CC.size();
            for (int i = begin; i < lastSize; i++) {
                Set<String> s = this.CC.get(i);
                for (String item : s) {
                    String[] strs = item.split(separationCharacter);
                    int productionIdx = Integer.parseInt(strs[0]);
                    int exprIdx = Integer.parseInt(strs[1]);
                    int placeholder = Integer.parseInt(strs[2]);
                    int prospectiveSymbol = Integer.parseInt(strs[3]);
                    List<Integer> list = this.production.get(productionIdx).expr.get(exprIdx);
                    if (placeholder < list.size()) {
                        int x = list.get(placeholder);
                        Set<String> temp = this.go(s, x);
                        int CCj = this.CCcontainsTheSet(temp);
                        if (CCj == -1) {
                            CC.add(temp);
                            CCj = this.CC.size() - 1;
                        }
                        res.add(new Node(i, x, CCj));
                    } else {
                        res.add(new Node(i, prospectiveSymbol, ((productionIdx - 1 >= 0 ? this.preArr[productionIdx - 1] : 0) + exprIdx + 1) | MASK));
                        if (productionIdx == this.startIndex) {
                            endState = i;
                        }
                    }
                }
                begin = lastSize;
            }
        }
        this.createActionAndGotoTable(res, endState);
    }

    private int CCcontainsTheSet(Set<String> set) {
        for (int i = 0; i < CC.size(); i++) {
            Set<String> s = CC.get(i);
            if (s.size() == set.size() && set.containsAll(s)) {
                return i;
            }
        }
        return -1;
    }

    private void createActionAndGotoTable(List<Node> node, int endState) {
        this.Action = new int[this.CC.size()][this.T.size()];
        for (int i = this.CC.size() - 1; i >= 0; i--) {
            for (int j = this.T.size() - 1; j >= 0; j--) {
                this.Action[i][j] = -1;
            }
        }
        this.Goto = new int[this.CC.size()][this.production.size()];
        for (int i = this.CC.size() - 1; i >= 0; i--) {
            for (int j = this.production.size() - 1; j >= 0; j--) {
                this.Goto[i][j] = -1;
            }
        }
        for (Node n : node) {
            if (this.isT(n.sym)) {
                Action[n.state][n.sym & DECODE] = n.val;
            } else {
                Goto[n.state][n.sym] = n.val;
            }
        }
        this.Action[endState][this.eof & DECODE] = Integer.MIN_VALUE;
        return;
    }

    public void printActionAndGotoTable() {
        if (this.Action == null || this.Goto == null) {
            System.out.println("表未生成,请使用toLRTable函数生成表。");
            return;
        }
        System.out.println("Action表如下");
        System.out.print("\t");
        for (int i = 0; i < this.T.size(); i++) {
            if (i != (this.epsilon & DECODE)) {
                System.out.print(this.T.get(i) + "\t");
            }
        }
        System.out.print("\n");
        for (int i = 0; i < this.Action.length; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < this.Action[i].length; j++) {
                if (j != (this.epsilon & DECODE)) {
                    if (this.Action[i][j] == -1) {
                        System.out.print("  \t");
                    } else if (this.Action[i][j] == Integer.MIN_VALUE) {
                        System.out.print("acc\t");
                    } else if ((this.Action[i][j] & MASK) == MASK) {
                        System.out.print("r" + (this.Action[i][j] & DECODE) + "\t");
                    } else {
                        System.out.print("s" + this.Action[i][j] + "\t");
                    }
                }
            }
            System.out.print("\n");
        }
        System.out.println("Goto表如下");
        System.out.print("\t");
        for (int i = 0; i < this.production.size(); i++) {
            System.out.print(this.production.get(i).name + "\t");
        }
        System.out.print("\n");
        for (int i = 0; i < this.Goto.length; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < this.Goto[i].length; j++) {
                if (this.Goto[i][j] == -1) {
                    System.out.print("  \t");
                    continue;
                }
                System.out.print("s" + this.Goto[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    class NTNode {
        private String name;
        private List<List<Integer>> expr;

        public NTNode(String name) {
            expr = new ArrayList<List<Integer>>();
            this.name = name;
        }

        public int addExpr() {
            expr.add(new ArrayList<Integer>());
            return expr.size() - 1;
        }

        public void addExprElement(int idx, int value) {
            this.expr.get(idx).add(value);
        }

        public void addExprElement(int value) {
            this.addExprElement(this.expr.size() - 1, value);
        }

        public void printNTNode() {
            System.out.println("NTNumber : " + this.name);
            for (List<Integer> list : this.expr) {
                for (Integer val : list) {
                    System.out.print(val + " ");
                }
                System.out.println();
            }
        }
    }

    class Node {
        int state;
        int sym;
        int val;

        public Node(int state, int sym, int val) {
            this.state = state;
            this.sym = sym;
            this.val = val;
        }
    }
}
