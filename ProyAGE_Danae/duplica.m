function [fitnessC,genomaC]=duplica(fitness,genoma)

fitnessC=zeros(round(size(fitness,1)/2),1);
genomaC=zeros(round(size(fitness,1)/2),size(genoma,2));

for i=1:round(size(fitness,1)/2)
    fitnessC(i,1)=fitness(i,1);
    for j=1:size(genoma,2)
        genomaC(i,j)=genoma(i,j);
    end
end
end
