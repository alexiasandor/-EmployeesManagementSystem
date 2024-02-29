package BusinessLogic.validators;

import Model.Comanda;

public class CantitateValidator {
    private static final int MIN_ORDER =7;
    private static final int MAX_ORDER =100;

    /**
     *
     * @param t
     */
    public void validate(Comanda t){
        if(t.getCantitate()<MIN_ORDER||t.getCantitate()>MAX_ORDER){
            throw new IllegalArgumentException("Nu aveam acea cantitate");
        }
    }
}
