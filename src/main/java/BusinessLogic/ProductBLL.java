package BusinessLogic;

import BusinessLogic.validators.Validator;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductDAO productDao;
    private List<Validator<Product>> validatorList;

    /**
     *
     */
    public ProductBLL() {
        productDao = new ProductDAO();
       validatorList = new ArrayList<Validator<Product>>();
    }

    /**
     *
     * @param id
     * @return
     * @throws NoSuchFieldError
     */
    public Product findById(int id) throws NoSuchFieldError{
        Product product =productDao.findById(id);
        if(product ==null)
            throw new NoSuchElementException("Produsul nu a fost gasit");
        return product;

    }

    public List<Product> findAllProducts(){
        List<Product> productList= productDao.findAll();
        if(productList == null){
            throw new NoSuchElementException("Lista de produse goala!");
        }
        return productList;
    }



    /**
     *
     * @param product
     */
    public void insertProduct (Product product){
        for (Validator<Product> validator : validatorList) {
            validator.validate(product);
        }
        productDao.insert(product);
    }

    /**
     *
     * @param id
     */
    public void deleteProduct (int id){
        productDao.delete(id);
    }

    public void updateProduct(Product product,int id, List<String>fields)
    {
        productDao.update(product,id,fields);
    }

}
