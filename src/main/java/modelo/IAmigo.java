package modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IAmigo extends Remote {

    public String getNomeAmigo() throws RemoteException;

    public void setNomeAmigo(String nomeAmigo) throws RemoteException;

    public String getTelefone() throws RemoteException;

    public void setTelefone(String telefone) throws RemoteException;

    public int getIdAmigo() throws RemoteException;

    public void setIdAmigo(int idAmigo) throws RemoteException;

    public List<String[]> listarTodos() throws RemoteException;

    public boolean insertAmigoDB(String nome, String telefone) throws RemoteException;

    public boolean deleteAmigoDB(int id) throws RemoteException;

    public boolean updateAmigoDB(int id, String nome, String telefone) throws RemoteException;

    public Amigo retrieveAmigoDB(int id) throws RemoteException;

    public int maiorID() throws RemoteException;

    public List<String[]> buscarEmprestimosDoAmigo(int id, boolean apenasAtivos) throws RemoteException;

    public boolean possuiEmprestimoAtivo(int id) throws RemoteException;

    public int quantidadeDeEmprestimos(int id) throws RemoteException;

    public String obterNomePorId(int id) throws RemoteException;

}
