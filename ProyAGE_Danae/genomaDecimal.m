function gD=genomaDecimal(genoma)

 Li=size(genoma,2)/4; %n=4
 Li2=Li;
 gD=zeros(round(size(genoma,1)),4);
 j=1;
 x=zeros(1,Li);
 m=1; 
 for i=1:round(size(genoma,1)/2)
     for k=1:4  %n=4
        Li=(k)*Li2; 
        while(j<=Li)
            x(m)=genoma(i,j);
            j=j+1;
            m=m+1;
        end
         gD(i,k)=convierteDec(x);
         j=Li+1; %creo que no es necesario
         m=1;
     end
     j=1;
     Li=size(genoma,2)/4; %creo que no es necesario tampoco
     m=1;
 end
end
 
 

  