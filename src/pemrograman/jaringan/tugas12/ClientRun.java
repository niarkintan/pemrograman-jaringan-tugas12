package pemrograman.jaringan.tugas12;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class ClientRun implements Runnable{
    private Client client;
    private portGui portView;
    private PesanGui view;
    private String mess = "";
    
    public ClientRun(){
        this.client = new Client();
        this.portView = new portGui();
        this.view = new PesanGui();
        this.portView.setTitle("Port Input");
        this.portView.setVisible(true);
        
        this.portView.getPortOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(String.valueOf(portView.getPortText().getText()).equals("6666")){
                    client.startConnection("127.0.0.1", Integer.valueOf(portView.getPortText().getText()));
                    portView.setVisible(false);
                    view.setTitle("Client Chat");
                    view.setVisible(true);
                }else{
                    portView.getPortText().setText("");
                }
            }
        });
        
        this.view.getKirimButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mess += time()+"-> "+String.valueOf(view.getTextField().getText()+"\n");
                String response = client.sendMessage(time()+"<- "+ String.valueOf(view.getTextField().getText()));
                mess += response + "\n";
            }
        });
    }
    
    public String time(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = simpleDateFormat.format(date);
        return time;
    }
    
    @Override
    public void run() {
        do{
            if(this.view.getTextArea().getText().equals(mess)==false){
                this.view.getTextArea().setText(mess);
            }
        }while(true);
    }
    
    public static void main(String[] args) {
        new Thread(new ClientRun()).start();
    }

        
}
