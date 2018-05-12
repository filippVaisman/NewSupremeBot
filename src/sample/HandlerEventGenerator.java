package sample;

import Tests.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.web.WebEngine;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLSelectElement;
import sample.JavaScript.SimpleDomHandler;

public class HandlerEventGenerator {

    private SimpleDomHandler domHandler;
    private DataProductLoader productLoader;
    private DataUserLoader userLoader;
    private Logger logger;
    private Actions actions;

    public HandlerEventGenerator(SimpleDomHandler domHandler) {
        actions = new Actions(domHandler);
    }

    public EventHandler<ActionEvent> getProductHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actions.getProduct();
            }
        };
    }


    public EventHandler<ActionEvent> getAddToCartHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actions.addToCart();
            }
        };

    }

    public EventHandler<ActionEvent> start(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actions.buy();
            }
        };
    }


    public EventHandler<ActionEvent> getBuyHandler(){

        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actions.buy();
            }
        };

    }


}
