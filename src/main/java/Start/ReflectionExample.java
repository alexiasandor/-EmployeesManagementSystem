package Start;

import java.lang.reflect.Field;

public class ReflectionExample {
    public void retriveProperties(Object object )
    {
        for(Field field: object.getClass().getDeclaredFields()){
            field.setAccessible(true);
            Object value ;
            try{
                value = field.get(object);
                System.out.println(field.getName()+ "=" +value);
            }catch(IllegalArgumentException e){
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }
}
