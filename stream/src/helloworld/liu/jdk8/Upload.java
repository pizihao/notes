package helloworld.liu.jdk8;

import jdk.nashorn.internal.objects.annotations.Function;

/**
 * @program: JVMDome
 * @description: 下载类实体
 * @author: liuwenhao
 * @create: 2020-11-10 18:08
 **/
public class Upload {
    /*模板名称*/
    private String templateName;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Upload(){

    }

    public Upload(String templateName){
        this.templateName = templateName;
    }

    public void exportlist(Uploadable uploadable){
        uploadable.uploadFile(templateName);
    }
}