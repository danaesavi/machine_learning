function gD=primerDec(genoma)
    Li=size(genoma,2)/4; %n=4
     Li2=Li;
     gD=zeros(1,4);
     j=1;
     x=zeros(1,Li);
    m=1;
     for k=1:4  %n=4
            Li=(k)*Li2; 
            while(j<=Li)
                x(m)=genoma(1,j);
                j=j+1;
                m=m+1;
            end
             gD(1,k)=convierteDec(x);
             j=Li+1;
             m=1;
     end
     
         
end

