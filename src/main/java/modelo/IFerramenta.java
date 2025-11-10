package modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IFerramenta extends Remote {

    public String getNomeFerramenta() throws RemoteException;

    public String buscarNomePorId(int id) throws RemoteException;

    public void setNomeFerramenta(String nome) throws RemoteException;

    public double getCustoFerramenta() throws RemoteException;

    public void setCustoFerramenta(double custo) throws RemoteException;

    public boolean salvar() throws RemoteException;

    public boolean atualizar() throws RemoteException;

    public boolean deletar() throws RemoteException;

    public Ferramenta buscarPorId(int id) throws RemoteException;

    public String[] listarTodas() throws RemoteException;

    public int proximoID() throws RemoteException;

    public boolean insertFerramentaDB(String nome, String marca, double custo) throws RemoteException;

    public boolean updateFerramentaDB(int id, String nome, String marca, double custo) throws RemoteException;

    public boolean deleteFerramentaDB(int id) throws RemoteException;

    public String getDisponivel() throws RemoteException;

    public boolean possuiEmprestimoAtivo() throws RemoteException;

    public int quantidadeEmprestimo() throws RemoteException;

    public String getMarcaFerramenta() throws RemoteException;

    public void setMarcaFerramenta(String marca) throws RemoteException;

    public int getIdFerramenta() throws RemoteException;

    public void setIdFerramenta(int idFerramenta) throws RemoteException;
}
