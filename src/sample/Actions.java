package sample;


import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLSelectElement;
import sample.JavaScript.SimpleDomHandler;

public class Actions {

    private SimpleDomHandler domHandler;
    private DataProductLoader productLoader;
    private DataUserLoader userLoader;
    private Logger logger;

    public Actions(SimpleDomHandler domHandler) {
        this.domHandler = domHandler;
        this.productLoader = DataProductLoader.getDataProductLoader();
        this.userLoader = DataUserLoader.getDataUserLoader();
        this.logger = new Logger();
    }

    public void getProduct(){
        String className = "name";

        int length = domHandler.getElementsLengthByClass(className);

        for(int i =0 ; i < length; i++){
            String s1 = domHandler.getElementByClass(className,i).getTextContent() ;
            String s2 = productLoader.getProductName();
            double d1 = WordEvaluater.evaluateSimilarytyByChars(s1,s2);
            double d2 =  WordEvaluater.evaluateSimilarytyByWords(s1,s2);
            System.out.println(d1+" "+d2);
            if(WordEvaluater.evaluateStrings(s1,s2)>0.8){
                domHandler.clickElementByClass(className,i);
                break;
            }
        }
    }

    public void addToCart(){
        findColor();
        findSize();
        domHandler.clickElementByClass("cart-button",0);
        new Thread(()->{
            try {
                //Thread.sleep(500);
                domHandler.clickElementById("checkout-now");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    public void buy(){
        domHandler.execute("document.getElementById('order_billing_name').value='"+userLoader.getName()+" "+userLoader.getSurname()+"'");

        domHandler.execute("document.getElementById('order_email').value='"+userLoader.getEmail()+"'");

        domHandler.execute("document.getElementById('order_tel').value='"+userLoader.getTelephone()+"'");

        domHandler.execute("document.getElementById('order_billing_address').value='"+userLoader.getAddress()+"'");
        domHandler.execute("document.getElementById('order_billing_city').value='"+userLoader.getCity()+"'");
        domHandler.execute("document.getElementById('order_billing_zip').value='"+userLoader.getPostCode()+"'");
        domHandler.execute("document.getElementById('credit_card_n').value='"+userLoader.getcreditCardNumber()+"'");
        domHandler.execute("document.getElementById('credit_card_cvv').value='"+userLoader.getCvv()+"'");



        domHandler.execute(String.format("document.getElementById('order_billing_country').value='%s'",userLoader.getCountry()));
        domHandler.clickElementById("order_terms");
        domHandler.execute(String.format("document.getElementById('credit_card_type').value='%s'",userLoader.getCardType()));
        domHandler.execute(String.format("document.getElementById('credit_card_month').value='%s'",userLoader.getmonthCardValid()));
        domHandler.execute(String.format("document.getElementById('credit_card_year').value='%s'",userLoader.getYearCardValid()));



        //Controls

        logger.log("Controls");

        logger.log("Country: "+((HTMLSelectElement)(domHandler.execute("document.getElementById('order_billing_country')"))).getValue());
        logger.log("Card type: "+((HTMLSelectElement)domHandler.execute("document.getElementById('credit_card_type')")).getValue());
        logger.log("Card valid month: "+((HTMLSelectElement)domHandler.execute("document.getElementById('credit_card_month')")).getValue());
        logger.log("Card valid year: "+((HTMLSelectElement) domHandler.execute("document.getElementById('credit_card_year')")).getValue());
        // document.getElementById('credit_card_n').value="5169310006155203";
        // $('#credit_card_type').get(0).selectedIndex =2;
        // $('#order_billing_country').val('PL');
    }


    private void findSize(){
        int length = domHandler.getElementLengthById("size-options");
        for (int i =0 ; i < length;i++){
            if(domHandler.execute(String.format("document.getElementById('size-options').children[%d].text",i)).equals(productLoader.getSize())){
                domHandler.execute(String.format("document.getElementById('size-options').value = document.getElementById('size-options').children[%d].value;",i));
                logger.log("Size found");
            }
        }
    }

    private void findColor(){

        int length = domHandler.getElementsLengthByClass("style-images");
        logger.log(length);
        for(int i =0 ; i < length; i++){
            if(!domHandler.getElementById("style-name").getTextContent().equals(productLoader.getColor())){
                try {
                    domHandler.execute(String.format("document.getElementsByClassName('style-thumb')[%d].click();",i));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                logger.log("Color found");
                break;
            }
        }

    }
}
