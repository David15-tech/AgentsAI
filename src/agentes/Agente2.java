/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import contenidoSerializado.Sensores;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davpa
 */
public class Agente2 extends Agent{

    
    //necesita un comportamiento
    @Override
    protected void setup() {//metodo que se ejecuta siempre primero, todo lo que agamos fuera hay que meterlo a llamar aqui
        //configuracion inicial
        addBehaviour(new comportamiento());
    }
    //tenemos los comportamientos, que se pueden controlar del agente sea ciclico secuencial, etc
    //podemos crear una clase(interna subclase) solo de comportamientos
    
    class comportamiento extends CyclicBehaviour{
        
        String verf[] = new String[2];

        @Override
        public void action() {
            //todo lo que necesite hacer el agente, red neuronal, AG, Bayes, if-else
            System.out.println(getName());
            ACLMessage msj = blockingReceive();//bloqueado hasta que reciba el mensaje, el metodo arroja un ACLMESSAJE
            
            String temperatura = msj.getContent();//que temperatura ha llegado, gracias al mensaje recibido...
            System.out.println("\n\n\nTEMPERATURA\n"+temperatura+"\n\n\n");
            String idConv = msj.getConversationId();
            
            if(idConv.equalsIgnoreCase("COD0102")){ 
                
                //verf[0] = temperatura+"";
                //if (Integer.parseInt(temperatura)>35){  //este if se usa para el mensaje que solo es enviado con un solo dato(String)
                    try {
                        //System.out.println("temp mayor a 35, abriendo ventiladores");
                        //necesitamos "actuadores", en este caso algo que aga que el ventilado se prenda....
                        //........
                        
                        
                        //Mensajes.enviar(ACLMessage.INFORM, "Receptor de info", "Prendiendo ventiladores", "COD0201", getAgent());//receptor enviando mensaje
                        System.out.println("===========MSG AGENTE 2==================");
                        System.out.println(msj);

                        Sensores s = (Sensores) msj.getContentObject(); // se hace un casting del msg, al parecer este lleva el objeto, pero serializado, solo hay que transformarlo
                        
                        System.out.println("======RIEGO  ||||||  Temperatura=======");
                        System.out.println(s.getRiego()+" "+s.getTemperatura());
                        
                    } catch (UnreadableException ex) {
                        Logger.getLogger(Agente2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //}
                }else {
                    if(idConv.equalsIgnoreCase("COD0302")){//habra que revisar, el agente 3, por el momento no se esta enviando mensajes desde ahi
                        String  humedadHoja = msj.getContent();
                        verf[1] = humedadHoja;
                        if (humedadHoja.equalsIgnoreCase("alta")){
                            System.out.println("no regar");
                        }else {
                            System.out.println("regar");
                        }
                        Mensajes.enviar(ACLMessage.INFORM, "Ag3", "Estado de riesgo", "COD0203", getAgent());
                    }
                }
            
            if(verf[0] != null){
                if(verf[1] != null){
                    String  mens = msj.getContent();
                    Mensajes.enviar(ACLMessage.INFORM, "Ag4", "Hola 4", "COD0204", getAgent());
                }
            }
            
        }
            //System.out.println(msj.getConversationId());
    }
}
    
