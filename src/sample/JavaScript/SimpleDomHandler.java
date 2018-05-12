package sample.JavaScript;

import javafx.scene.web.WebEngine;
import org.w3c.dom.Element;
import sample.JavaScript.interfaces.DomHandler;

public class SimpleDomHandler implements DomHandler{

    private WebEngine engine;
    private SimpleScriptGenerator scriptGenerator;


    public SimpleDomHandler(WebEngine engine) {
        this.engine = engine;
        scriptGenerator = new SimpleScriptGenerator();
    }

    @Override
    public Element getElementById(String id) {
        return (Element) engine.executeScript(scriptGenerator.getElementById(id));
    }

    @Override
    public Element getElementByClass(String className, int index) {
        try {
            return (Element) engine.executeScript(scriptGenerator.getElementByClass(className,index));
        }catch (Exception e){
            System.out.println("Cast exception");
            return null;
        }
    }

    @Override
    public int getElementsLengthByClass(String className) {
        return Integer.parseInt(engine.executeScript(scriptGenerator.getElementsByClassLength(className)).toString());
    }

    @Override
    public void clickElementById(String id) {
        engine.executeScript(scriptGenerator.clickElementById(id));
    }

    @Override
    public void clickElementByClass(String className, int index) {
        engine.executeScript(scriptGenerator.clickElementByClassName(className,index));
    }

    public int getElementLengthById(String id){
        return Integer.parseInt(engine.executeScript(String.format("document.getElementById('%s').length",id)).toString());
    }

    public Object execute(String javascriptCode){
        Object e = engine.executeScript(javascriptCode);
        return e;
    }

}
