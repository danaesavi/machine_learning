function genomaP=poblacionInicial(genomaP)
gen= randi([0 1],round(size(genomaP,1)/2),size(genomaP,2));
for i=1:round(size(genomaP,1)/2)
    for j=1:size(genomaP,2)
       genomaP(i,j)=gen(i,j);
    end
end
end    
    
