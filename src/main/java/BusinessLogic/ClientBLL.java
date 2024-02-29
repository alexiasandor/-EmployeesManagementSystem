package BusinessLogic;

import BusinessLogic.validators.EmailValidator;
import BusinessLogic.validators.Validator;
import DataAccess.ClientDAO;
import Model.Client;
import BusinessLogic.validators.AgeValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private ClientDAO clientDao;
    private List<Validator<Client>> validatorList;

    /**
     *
     */
    public ClientBLL(){
        validatorList=new ArrayList<Validator<Client>>();
        validatorList.add(new EmailValidator());
        validatorList.add(new AgeValidator());
        clientDao= new ClientDAO();
    }

    /**
     *
     * @param id
     * @return
     */
    public Client findById(int id) throws NoSuchElementException{
        Client client =clientDao.findById(id);
        if(client ==null)
            throw new NoSuchElementException("Clientul nu a fost gasit");
        return client;

    }

    /**
     * afisarea tuturor clientilor
     * @return
     */
    public List<Client> findAllClients(){
        List<Client> clientList= clientDao.findAll();
        if(clientList == null){
            throw new NoSuchElementException("Lista de clienti goala!");
        }
        return clientList;
    }

    /**
     * inseram un nou client
     * @param client
     */
    public void insertClient (Client client){
       for(Validator<Client> v :validatorList){
           v.validate(client);
       }
       clientDao.insert(client);
    }

    /**
     * stergere client
     * @param id
     */
    public void deleteClient (int id){
        clientDao.delete(id);
    }

    /**
     * update client, in functie de un anumit parametru
     * @param client
     * @param id
     */
    public void updateClient(Client client, int id, List<String>fileds)
    {
        clientDao.update(client, id, fileds);
    }
}
