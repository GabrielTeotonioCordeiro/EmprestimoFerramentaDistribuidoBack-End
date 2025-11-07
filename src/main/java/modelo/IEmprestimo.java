package modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IEmprestimo extends Remote {

    public boolean insertEmprestimoDB(int idAmigo, int idFerramenta, String dataEmprestimo) throws RemoteException;

    public boolean updateEmprestimoDB(int id, int idAmigo, int idFerramenta, String dataEmprestimo, String dataDevolucao) throws RemoteException;

    public boolean deleteEmprestimoDB(int id) throws RemoteException;


    public String emprestimoAtivo(int idEmprestimo) throws RemoteException;

    
    public String getDataEmprestimo() throws RemoteException;
    
    public int getIDEmprestimo() throws RemoteException;
    
    public void setIDEmprestimo(int idEmprestimo)throws RemoteException;
    
    public int getIDAmigo() throws RemoteException;
    
    public void setIDAmigo(int idAmigo) throws RemoteException;
    
     public int getIDFerramenta()throws RemoteException;
     
     public void setIDFerramenta(int idFerramenta)throws RemoteException;
     
     public void setDataEmprestimo(String dataEmprestimo) throws RemoteException;
     
     public String getDataDevolucao()throws RemoteException;
     
     public void setDataDevolucao(String dataDevolucao)throws RemoteException;
     
     public Emprestimo retrieveEmprestimoDB(int idEmprestimo) throws RemoteException;
     
     public int maiorID()throws RemoteException;
     
     public List<Emprestimo> getListaEmprestimoAtivo() throws RemoteException;
     
     
}
