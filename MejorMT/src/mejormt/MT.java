/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mejormt;
import java.util.Random;
import java.util.Scanner;


public class MT {
    
    short cinta[];           //cinta en donde se escribe lo que las MT escriben
    short cintaObj[];        //cinta a la que se quiere llegar (objetivo)
    int longitud;            //longitud predeterminada de la cinta
    int numEstados;          //número de estados de la MT
                             //NOTA: el estado de HALT es (numEstados -1)
    int numPasos;            //número máximo de pasos
    int posInicial;          //posición inicial de la cabeza
    int tablaEdos0[][], tablaEdos1[][]; //tabla de estados que definen la MT
    int fitness; //número que indica la cercanía de la cinta con la cintaObj
    int mejortablaEdos0[][], mejortablaEdos1[][];//tabla edos. de la mejor MT
                                                 //hasta el momento
    int numSimActual;        //número de simulación actual
    int numSimMax;           //número predeterminado de simulaciones
    
    public MT() {
        longitud=3000;
        numSimMax=50000;
        cinta = new short[longitud];             //Restricción tamaño de la cinta (Lmax)
        cintaObj = new short[longitud];
        this.numEstados = 64;                    //Restricción número de estados (Smax)
        this.numPasos = 10000;               //Restricción número de pasos
        posInicial=(longitud/2)+1;               //Posición inicial de la cinta y posición donde debe quedar el resultado final
        tablaEdos0=new int[numEstados][8];       //Tabla para cuando la entrada es 0
        tablaEdos1=new int [numEstados][8];      //Tabla para cuando la entrada es 1
        mejortablaEdos0=new int[numEstados][8];  //Mejor tabla hasta el momento
        mejortablaEdos1=new int [numEstados][8];
        fitness=longitud+1;                   
    }
    
    /**
     * Método que genera la primer MT aleatoriamente.
     */
    public void generaMT(){
        Random r=new Random();
        
        for (int i=0;i<numEstados;i++){
            for(int j=0;j<16;j++){
            if (j < 8){
                tablaEdos0[i][j] = eligeNum(r.nextDouble());
                mejortablaEdos0[i][j]=tablaEdos0[i][j];//al principio la mejor es la primera
            }else{
                tablaEdos1[i][j-8] = eligeNum(r.nextDouble());
                mejortablaEdos1[i][j-8]=tablaEdos1[i][j-8];//al principio la mejor es la primera
            }
        }
    }
    }
    
    /**
     * Método que altera la MT aleatoriamente (escalador Random Mutation).
     */
    public void alteraMT(){
        
        
        //este for hace que lo que se altera siempre es la MEJOR MT hasta ahora
        //si se comenta este for, lo que se altera siempre es la ULTIMA MT
        /*for(int i=0;i<numEstados;i++){
             for(int j=0;j<8;j++){
                 tablaEdos0[i][j]=mejortablaEdos0[i][j];
                 tablaEdos1[i][j]=mejortablaEdos1[i][j];
             }
         }*/
         Random r=new Random();
         
         int tabla0o1=(int)(eligeNum(r.nextDouble()));
         int edoEscogido=(int)(r.nextDouble()*numEstados);
         int cEscogida=(int)(r.nextDouble()*8);
         
         if(tabla0o1==0){ 
                if(tablaEdos0[edoEscogido][cEscogida]==0){
                    tablaEdos0[edoEscogido][cEscogida]=1; 
                }
                else{
                    tablaEdos0[edoEscogido][cEscogida]=0;
                }
             
         }
         else{
                if(tablaEdos1[edoEscogido][cEscogida]==0){
                    tablaEdos1[edoEscogido][cEscogida]=1; 
                }
                else{
                    tablaEdos1[edoEscogido][cEscogida]=0;
                }
             }
             
     }
         
    /**
     * Método que simula la MT.
     * @return si la MT llega al estado de HALT o no (true o false)
     */
    public boolean simular(){
        boolean sePudo=true;
        alteraMT();
        
        int pos=posInicial;
        int estado=0,noPasos=0;
        
        while(sePudo&&estado!=(numEstados-1)&&noPasos<numPasos&&pos>=0&&pos<longitud){
        if(cinta[pos]==0){
            
                cinta[pos]=(short)(tablaEdos0[estado][0]);    //Escribe 
                double np = (tablaEdos0[estado][1]);  //Hacia dónde se mueve
                pos=pos-(int)Math.pow(-1, np); 
                noPasos++;                                                      //Aumenta el número de pasos realizados
                int a=0,num=0;
        
                for(int i=7;i>1;i--){
                    estado=num+(int)(Math.pow(2, a))*tablaEdos0[estado][i];
                    a++;
                }
                if(estado==(numEstados-1)){
                    System.out.println("Llegó a halt");
                    sePudo=true;
                }
                if(noPasos>=numPasos){
                     System.out.println("Demasiados pasos");
                    sePudo=false;
                    
                }
            
            }
            
        else{
            
                cinta[pos]=(short)(tablaEdos1[estado][0]);
                double np = (double)(tablaEdos1[estado][1]);
                pos=pos-(int)Math.pow(-1, np);
                noPasos++;
                int a=0,num=0;
                for(int i=7;i>1;i--){
                    estado=num+(int)(Math.pow(2, a))*tablaEdos1[estado][i];
                    a++;           
                }   
                    if(estado==(numEstados-1)){
                    System.out.println("Llegó a halt");
                    sePudo=true;
                }
                if(noPasos>=numPasos){
                     System.out.println("Demasiados pasos");
                    sePudo=false;
                    
                }
                      
            }
        }
        
        if(pos<0||pos>=longitud){
            sePudo=false; //se salió de la cinta
            
        }
                    
        return sePudo;
    
    
    }
    
    /**
     * Método que elige el número entero 0 o 1.
     * @param n número de tipo double entre 0 y 1
     * @return regresa el número entero 0 o 1
     */
    public int eligeNum(double n){
        if(n>=0.5)
            return 1;
        else
            return 0;
        
    }
    
    /**
     * Método que revisa el fitness de la cinta obtenida actual.
     * @return si el fitness mejoró con respecto a la mejor MT (true o false)
     */
   public boolean check_fitness(){
       int fitnessNueva=0;
       for(int i=posInicial;i<longitud;i++){//revisa a partir de la posInicial
                                            //la coincidencia "bit a bit" de 
                                            //la cinta con la cintaObj
           if(cinta[i]!=cintaObj[i]){
               fitnessNueva++;
           }
       }
       if(fitnessNueva<fitness){
           fitness=fitnessNueva;
           return true;
       }
       else
           return false;
       
   }
   
   /**
    * Método principal que busca la mejor MT.
    */
   public void busca_mejor(){
       generaMT();
       int i=0;
       while(i<numSimMax&&fitness!=0){
           System.out.println("Simulación no. "+i);
           if(simular()){
           if(check_fitness()){
               for(int j=0;j<numEstados;j++){
                   for(int k=0;k<8;k++){
                       mejortablaEdos0[j][k]=tablaEdos0[j][k];
                       mejortablaEdos1[j][k]=tablaEdos1[j][k];
                   }
               }
           }}
           reseteaCinta();
         i++;
       }
       
       barrido();
   }
   
   /**
    * Método que resetea la cinta a puros ceros.
    */
   public void reseteaCinta(){
       for(int j=0;j<cinta.length;j++){
           cinta[j]=0;
       }
   }
   
   /**
    * Método que revisa los estados de la mejor MT y se queda únicamente
    * con los estados visitados.
    */
   public void barrido(){
       boolean sePudo=true;
        int pos=posInicial;
        int estado=0,noPasos=0;
        int estadosVisitados[]=new int[numEstados];
        
        while(sePudo&&estado!=(numEstados-1)&&noPasos<numPasos&&pos>=0&&pos<longitud){
        if(cinta[pos]==0){
            
                cinta[pos]=(short)(mejortablaEdos0[estado][0]);      //Escribe 
                double np = (mejortablaEdos0[estado][1]);
                pos=pos-(int)Math.pow(-1, np);                                  
                noPasos++;                                                      //Aumenta el número de pasos realizados
                int a=0,num=0;
        
                for(int i=7;i>1;i--){
                    estado=num+(int)(Math.pow(2, a))*mejortablaEdos0[estado][i];
                    a++;
                }
                estadosVisitados[estado]=estado;
                if(estado==(numEstados-1)){
                    System.out.println("Llegó a halt");
                    sePudo=true;
                }
                if(noPasos>=numPasos){
                     System.out.println("Demasiados pasos");
                    sePudo=false;
                }
            
            }
            
        else{
                cinta[pos]=(short)(mejortablaEdos1[estado][0]);
                double np = (double)(mejortablaEdos1[estado][1]);
                pos=pos-(int)Math.pow(-1, np);
                noPasos++;
                  int a=0,num=0;
                   for(int i=7;i>1;i--){
                    estado=num+(int)(Math.pow(2, a))*mejortablaEdos1[estado][i];
                    a++;
                }
                estadosVisitados[estado]=estado;
                if(estado==(numEstados-1)){
                    System.out.println("Llegó a halt");
                    sePudo=true;
                }
                if(noPasos>=numPasos){
                     System.out.println("Demasiados pasos");
                    sePudo=false;
                    
                }
                      
            }
        }
        if(pos<0||pos>=longitud){
            sePudo=false; //se salió de la cinta
        }
        
        int tablaEdosFinalT0[][]=new int[numEstados][8];
        int tablaEdosFinalT1[][]=new int[numEstados][8];
       
        
        //se va a guardar cuáles son los nuevos números de los estados,
        //ya que se "borren" los estados por los que nunca se pasa
        int viejoEdoNuevoEdo[]=new int[numEstados];    
        for(int a=0;a<numEstados;a++){
            viejoEdoNuevoEdo[a]=-1;//nuevo estado, que antes era el estado a; 
                                //si es un estado "borrado", toma el valor de -1
        }
        
        int nEdV=0;
      for(int a=0;a<numEstados;a++){
         if(a==0||estadosVisitados[a]!=0){
             for(int b=0;b<8;b++){
             tablaEdosFinalT0[nEdV][b]=mejortablaEdos0[a][b];
             tablaEdosFinalT1[nEdV][b]=mejortablaEdos1[a][b];
             }
             viejoEdoNuevoEdo[a]=nEdV++;
         }
      
     }
       
      int viejoEdo=0,nuevoEdo,nume,pot;
      int nuevoEdoB2[]=new int[6];//nuevo estado, en base 2
      int tablaEdosFinal[][]=new int[nEdV][16];
      //int tablaEdosFinal1[][]=new int[nEdV][8];
      
      for(int a=0;a<nEdV;a++){
             for(int b=0;b<8;b++){//primero, copia a tablaEdosFinal lo que
                                  //tenías en las otras dos (T0, T1)
             tablaEdosFinal[a][b]=tablaEdosFinalT0[a][b];
             tablaEdosFinal[a][b+8]=tablaEdosFinalT1[a][b];
             }
             pot=0;nume=0;
             for(int i=7;i>1;i--){//segundo, tomar el viejo estado...
                viejoEdo=nume+(int)(Math.pow(2, pot))*tablaEdosFinal[a][i];
                pot++;
             }
             nuevoEdo=viejoEdoNuevoEdo[viejoEdo];
             nuevoEdoB2=aBinario(nuevoEdo,6);
             for(int i=7;i>1;i--){//...y sustituirlo por el nuevo estado
                tablaEdosFinal[a][i]=nuevoEdoB2[i-2];
             }
             
             pot=0;nume=0;
             for(int i=15;i>9;i--){//segundo, tomar el viejo estado...
                viejoEdo=nume+(int)(Math.pow(2, pot))*tablaEdosFinal[a][i];
                pot++;
             }
             nuevoEdo=viejoEdoNuevoEdo[viejoEdo];
             nuevoEdoB2=aBinario(nuevoEdo,6);
             for(int i=15;i>9;i--){//...y sustituirlo por el nuevo estado
                tablaEdosFinal[a][i]=nuevoEdoB2[i-10];
             }
         }
      
      System.out.println("La tabla final de estados: ");
       for (int k = 0; k < nEdV; k++) {
            for (int l = 0; l < 16; l++) {
                System.out.print(tablaEdosFinal[k][l]);
                 
                
                if (l == 15) {
                    System.out.println("");
                }
            }
        }
       numEstados=nEdV;//cambia el número de estados; cambia entonces el estado de halt
       
       //Informar al usuario si la "mejor" MT realmente resuelve o no el problema
       if(sePudo){
           System.out.println("Es una MT que llega a halt");
       }else{
           System.out.println("No llega a HALT esta MT");
       }
      }
   /**
    * Método que convierte de decimal a binario de longitud n
    * @param dec número a convertir
    * @param n longitud del binario resultante
    * @return número en binario, en un arreglo
    */
   public int[] aBinario(int dec,int n){
      int bin[]=new int[n];
      int pow;
      for(int i=n-1;i>=0;i--){
          pow=(int)Math.pow(2,i);
          if(dec-pow>=0){
              dec-=pow;
              bin[n-1-i]=1;
          }
          else{
              bin[n-1-i]=0;
          }
      }
      return bin;
   }
      
   /**
    * Método que establece la cinta objetivo de acuerdo a la cadena que 
    * el usuario quiere, convertida a ASCII.
    * @param cad cadena que el usuario quiere
    */
    public void set_cintaObj(String cad){
        
    int next;
    int posicion=posInicial;
    int nextBinario[]=new int[8];
    for(int i=0;i<cad.length();i++){
               next=(int)cad.charAt(i);
               nextBinario=aBinario(next,8);
               for(int j=0;j<8;j++){
                   cintaObj[posicion+j]=(short)nextBinario[j];
               }
              posicion+=8;
    }
   }
                   
   //Método main
   public static void main(String[] args) {
            MT mt=new MT();
            Scanner input = new Scanner( System.in );
            String cad;
            System.out.print("Enter the text you want to generate with a Turing Machine ");
            cad = input.next( );
            mt.set_cintaObj(cad);
            mt.busca_mejor();
       
            
        }
    }
            

    

