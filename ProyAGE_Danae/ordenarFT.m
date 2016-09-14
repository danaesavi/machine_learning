function [fitness,genoma]=ordenarFT(fitness,genoma)

genomaAux=zeros(round(size(genoma,1)/2),size(genoma,2));
for i=1:round(size(genoma,1)/2)
    for j=1:size(genoma,2)
        genomaAux(i,j)=genoma(i,j);
    end
end

fitnessAux=zeros(round(size(fitness,1)/2),1);
for j=1:round(size(fitness,1)/2)
    
        fitnessAux(j,1)=fitness(j,1);
        
end   

 FG=horzcat(fitnessAux,genomaAux);
 FGo=sortrows(FG);
 Fo=FGo(:,1);
 Go=FGo(:,2:size(genoma,2)+1);

 
 for i=1:round(size(genoma,1)/2)
      for j=1:round(size(genoma,2))
        genoma(i,j)=Go(i,j);
     end
 end
  
 for k=1:round(size(fitness,1)/2)
     fitness(k,1)=Fo(k,1);
 end
 end