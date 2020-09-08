package com.liu.test.LR;

/**
 * @author shidacaizi
 * @date 2020/5/23 15:51
 */
public class CodeAnalyzer {
    String left;
    private char[] text;
    private int textSize = 0;
    private int point = 0;
    private BnfContainer bc;
    private Token token;
    private int count = 0;
    public CodeAnalyzer(String text, BnfContainer bc) {
        this.bc = bc;
        this.initText(text);
        this.initStartSymbol();
        this.initCodeAnalyzer();
    }

    private void initText(String s) {
        this.text = s.toCharArray();
        int idx = 0;
        while (idx < text.length) {
            if (text[idx] == '\r' || text[idx] == '\n' || text[idx] == '\t' || text[idx] == ' ') {
                idx++;
            } else {
                text[textSize++] = text[idx++];
            }
        }
    }

    private void initStartSymbol() {
        point = 0;
        char[] needle = {'s', 't', 'a', 'r', 't', ':', '<'};
        if (textSize <= needle.length) {
            this.notFindStartNT();
        }
        point = 0;
        while (point < needle.length) {
            if (needle[point] == text[point]) {
                point++;
            } else {
                this.notFindStartNT();
            }
        }
        point = needle.length;
        while (point < textSize && text[point] != '>') {
            point++;
        }
        this.bc.setStart(new String(text, needle.length, point - needle.length));
        this.skip(Type.RT);
        this.skip(Type.SEMICOLON);
    }

    private void skip(Type t) {
        switch (t) {
            case LT:
                this.skip('<');
                break;
            case RT:
                this.skip('>');
                break;
            case OR:
                this.skip('|');
                break;
            case SEMICOLON:
                this.skip(';');
                break;
            case QUOTE:
                this.skip('"');
                break;
            case COLON:
                this.skip('-');
                break;
            case EQ:
                this.skip('>');
                break;
        }
    }

    private void skip(char c) {
        if (point >= this.textSize || this.text[point] != c) {
            System.out.println("第" + this.count + "个产生式,缺少符号  " + c);
            System.exit(-1);
        }
        point++;
    }

    private void notFindStartNT() {
        System.out.println("没有找到目标非终结符号!");
        System.exit(-1);
    }

    private void initCodeAnalyzer() {
        int idx = this.point;
        this.point = 0;
        this.count = 0;
        while (true) {
            while (this.point < textSize && text[this.point] != ';') {
                this.point++;
            }
            this.point++;
            this.count++;
            if (this.point >= textSize) {
                break;
            }
            String name = this.getNT();
            bc.addNT(name);
        }
        this.count = 0;
        this.point = idx;
    }

    public void analyze() {
        while (point < this.textSize) {
            this.count++;
            production();
        }
    }

    public void production() {
        this.left = this.getNT();
        this.skipDefineSymol();
        this.expr();
    }

    public void skipDefineSymol() {
        skip(Type.COLON);
        skip(Type.COLON);
        skip(Type.EQ);
    }

    public String getNT() {
        skip(Type.LT);
        StringBuilder res = new StringBuilder();
        while (this.point < this.textSize && text[this.point] != '>') {
            res.append(text[this.point++]);
        }
        skip(Type.RT);
        return res.toString();
    }

    public String getT() {
        this.skip(Type.QUOTE);
        StringBuilder res = new StringBuilder();
        while (this.point < this.textSize && this.text[this.point] != '"') {
            res.append(text[this.point++]);
        }
        this.skip(Type.QUOTE);
        return res.toString();
    }

    public void expr() {
        this.term();
        while (this.point < this.textSize && text[this.point] == '|') {
            this.skip(Type.OR);
            term();
        }
        this.skip(Type.SEMICOLON);
    }

    public void term() {
        bc.creatNewExper(this.left);
        while (this.point < this.textSize && (text[this.point] == '"' || text[this.point] == '<')) {
            factor();
            bc.addExpeElement(this.left, token.isNt, token.name);
        }
    }

    public void factor() {
        if (text[this.point] == '"') {
            String name = this.getT();
            this.token = new Token(false, name);
        } else {
            String name = this.getNT();
            token = new Token(true, name);
        }
    }

    enum Type {
        LT,
        RT,
        SEMICOLON,
        QUOTE,
        OR,
        COLON,
        EQ,
    }

    class Token {
        boolean isNt;
        String name;

        public Token(boolean isNt, String name) {
            this.isNt = isNt;
            this.name = name;
        }
    }
}

