function fitness=evalua(genoma,fitness)
gD=genomaDecimal(genoma);
s=0;
k=1000000000000000000000; %  castigo
c=8;    %8 es el número de restricciones n=4
k_c=k/c; 

for i=1:size(fitness,1)/2
    for j=1:size(gD,2)
        if(gD(i,j)>-5.12)
             s=s+1;
        end
        if( gD(i,j)<5.12)
                s=s+1;
        end
        
    end
    
    if(s==c)     
        fitness(i,1)=JongsFunction(gD(i,:));
    else
        fitness(i,1)=k-(s*k_c);  %Castigo quiero un número muy grande

    end
     s=0;
    
end