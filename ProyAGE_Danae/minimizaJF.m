function x=minimizaJF()
%Danae Sánchez 136040
%G es el número de generaciones
creaParams;
G=getParams;
[N_2,Nx2,genoma,fitness,Norm,L,L_2,B2M]=calcParams;

genoma=poblacionInicial(genoma);
fitness=evalua(genoma,fitness);
[fitnessO,genomaO]=ordenarFT(fitness,genoma); 

 for i=1:G(9)
    [fitnessC,genomaC]=duplica(fitnessO,genomaO); %Duplica los primeros N
    genomaCruza=cruza(genomaO);		%Cruza en los primeros N
    genomaCruzaMuta=muta(genomaCruza,B2M,N_2,L);		%Muta en los primeros N
    fitnessE=evalua(genomaCruzaMuta,fitness);
    [genoma,fitness]=combinaGen(genomaC,genomaCruzaMuta,fitnessC,fitnessE);
    [fitnessO,genomaO]=ordenar(fitness,genoma);
   
 end
  display('Mejor fitness');
  display(fitnessO(1,1));
  display('Cadena correspondente al mejor fitness')
  display(genomaO(1,:));
  display('Resultado');
  x=primerDec(genomaO);
 
end

