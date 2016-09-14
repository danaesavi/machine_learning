function [fitness,genoma]= ordenar(fitness,genoma)
 
 FG=horzcat(fitness,genoma);
 FGo=sortrows(FG);
 fitness=FGo(:,1);
 genoma=FGo(:,2:size(genoma,2)+1);
     
 end
 
 
 


   

