package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.rmi.*;
import java.util.LinkedList;

import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioComBroker;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.WorkerService;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.Client;


public class Broker implements ServicioComBroker, WorkerService {


	private static LinkedList<String> serverUris = new LinkedList();
	private static LinkedList<String> clientUris = new LinkedList();
	//private static LinkedList<String> clientUris = new LinkedList();

	private long puntosRetorno;

	private static LinkedList<Server> servers = new LinkedList();
	private static LinkedList<Client> clients = new LinkedList();


	public Server pedirPuntos() {
		
		int size=servers.size();
		if(size>0){
			Server ret=servers.poll();
			serverUris.add(serverUris.poll());
			servers.add(ret);
			return ret;
		}
		return null;
		
	}

	public synchronized void attachServicioGenerarPuntos(String uri) {
		try {
            Server server =(Server)Naming.lookup(uri);
            System.out.println("server :" + uri);
            serverUris.add(uri);
			servers.add(server);
            

        } catch (Exception e) {
            System.out.println("error al hacer binding: " + uri);
            e.printStackTrace();
        }
	}

	public synchronized void attachClient(String uri){

		try {
            Client client =(Client)Naming.lookup(uri);
            System.out.println("client :" + uri);
            clientUris.add(uri);
			clients.add(client);
            

        } catch (Exception e) {
            System.out.println("error al hacer binding: " + uri);
            e.printStackTrace();
        }

	}

    public synchronized void detachClient(String serverUri){

		int index = clientUris.indexOf(serverUri);
		String uriRemoved=clientUris.remove(index);
		clients.remove(index);
		assert(serverUri.equals(uriRemoved));	

	}

	public synchronized void detachServicioGenerarPuntos(String serverUri) {
		int index = serverUris.indexOf(serverUri);
		String uriRemoved=serverUris.remove(index);
		servers.remove(index);
		assert(serverUri.equals(uriRemoved));			
	}

	public boolean isServer(){
		return servers.size()>0;
	}

    public int getServers(){
		return servers.size();
	}

	public boolean isClient(){
		return clients.size()>0;
	}

    public int getClients(){
		return clients.size();
	}


}
