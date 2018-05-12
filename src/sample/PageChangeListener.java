package sample;


import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.w3c.dom.html.HTMLInputElement;
import sample.JavaScript.SimpleDomHandler;

public class PageChangeListener implements EventHandler<WebEvent<String>>{

    private WebView webView;
    private SimpleDomHandler domHandler;
    private DataProductLoader productLoader;
    private boolean actionPerformed;
    private DataUserLoader userLoader;
    private Actions actions;

    public PageChangeListener(WebView view, DataProductLoader productLoader,DataUserLoader userLoader){
        webView = view;
        domHandler = new SimpleDomHandler(view.getEngine());
        this.productLoader = productLoader;
        actionPerformed = false;
        this.userLoader = userLoader;
        actions = new Actions(domHandler);
    }



    @Override
    public void handle(WebEvent<String> event) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("check if cart-button exists");
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(domHandler.getElementByClass("cart-button",0)!=null&&actionPerformed==false){
                            findColor();
                            findSize();
                            domHandler.clickElementByClass("cart-button",0);
                            actionPerformed=true;


                            PauseTransition pause2 = new PauseTransition(Duration.seconds(0.1));



                            pause2.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("Iteration");
                                    if(domHandler.getElementById("checkout-now")!=null) {
                                        domHandler.clickElementById("checkout-now");
                                    }else{
                                        pause2.play();
                                    }
                                }
                            });
                            pause2.play();

                            PauseTransition pause3 = new PauseTransition(Duration.seconds(0.1));
                            pause3.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    if(domHandler.getElementById("order_billing_name")!=null)
                                        pasteInfo();
                                    else
                                        pause3.play();
                                }
                            });
                            pause3.play();

                        }
                    }
                });
                pause.play();


            }
        });
    }


    private void pasteInfo(){
        actions.buy();

    }

    private void findSize(){
        int length = domHandler.getElementLengthById("size-options");
        for (int i =0 ; i < length;i++){
            if(domHandler.execute(String.format("document.getElementById('size-options').children[%d].text",i)).equals(productLoader.getSize())){
                domHandler.execute(String.format("document.getElementById('size-options').value = document.getElementById('size-options').children[%d].value;",i));

            }
        }
    }

    private void findColor(){

        int length = domHandler.getElementsLengthByClass("style-images");
        for(int i =0 ; i < length; i++){
            if(!domHandler.getElementById("style-name").getTextContent().equals(productLoader.getColor())){
                try {
                    domHandler.execute(String.format("document.getElementsByClassName('style-thumb')[%d].click();",i));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                break;
            }
        }

    }

}
