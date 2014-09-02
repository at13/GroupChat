import java.net.*;
import java.io.*;
import java.util.*;

public class Server{
	private DataInputStream in;
	private DataOutputStream out;
	private Socket client;
	private Socket Client1;
	
	public Server(int port) throws IOException{
		ServerSocket sock=new ServerSocket(port);
		sock.setSoTimeout(500);
		ArrayList a=new ArrayList();
		ArrayList<Socket> a1= new ArrayList<Socket>();
		while(true){
			Boolean sucess=true;
			try{
				client=sock.accept();
			}
			catch(SocketTimeoutException e){
				sucess=false;
			}
			if(sucess)
			{
				in = new DataInputStream(client.getInputStream());
				String i=in.readUTF();
				a1.add(client);
				a.add(i);
			}
			Iterator<Socket> it=a1.iterator();
			Iterator it1=a.iterator();
			while(it.hasNext()){
				Socket Client=it.next();
				String name=(String)it1.next();
				in = new DataInputStream(client.getInputStream());
				if(in.available()>0)
				{
					String msg=in.readUTF();
					msg=name+msg;
					Iterator<Socket> it2=a1.iterator();
					Iterator it3=a.iterator();
					while(it2.hasNext())
					{
						Client1=it2.next();
						try{
							out = new DataOutputStream(Client1.getOutputStream());
							out.writeUTF(msg);
									
						}
						catch(Exception e){
							
						}
					}
				}
			}
			
			
		}
	}
	
	
	public static void main(String args[]) throws IOException
	{
		if(args.length!=1)
		{
			System.exit(1);
		}
		
		int port = 4000;
		
		new Server(port);
	}
}