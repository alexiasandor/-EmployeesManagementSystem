package BusinessLogic.validators;
import Model.Client;
public class AgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 7;
    private static final int MAX_AGE = 90;

    /**
     *
     * @param c
     */
    public void validate(Client c) {

        if (c.getVarsta() < MIN_AGE || c.getVarsta() > MAX_AGE) {
            throw new IllegalArgumentException("The Student Age limit is not respected!");
        }

    }

}