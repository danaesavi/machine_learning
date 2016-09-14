function [genoma,fitness]=combinaGen(genomaC,genoma,fitnessC,fitness)

genoma=vertcat(genoma(1:round(size(genoma,1)/2),:),genomaC);
fitness=vertcat(fitness(1:round(size(fitness,1)/2),:),fitnessC);


end