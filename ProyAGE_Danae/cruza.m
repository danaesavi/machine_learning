function genP=cruza(genP)
genoma=zeros(round(size(genP,1)/2),size(genP,2));
for i=1:round(size(genP,1)/2)
    for j=1:size(genP,2)
        genoma(i,j)=genP(i,j);
    end
end

Lx=round(size(genoma,2)/3);
LI=zeros(1,Lx);
LN=zeros(1,Lx);
MI=zeros(1,Lx);
MN=zeros(1,Lx);
RI=zeros(1,Lx);
RN=zeros(1,Lx);
N=size(genoma,1);
Pc=getParams;

for i=1:size(genoma,1)/2
    if (rand()>Pc(6))
        if(mod(size(genoma,1),2)==0 || (mod(size(genoma,1),2)~=0 && i~=round(size(genoma,1)/2)))
             r = round(2*rand(1,1) + 1);
             switch r
                case 1
                    for j=1:Lx
                        LI(j)=genoma(i,j);
                        LN(j)=genoma(N-i+1,j);
                    end
                    for k=1:Lx
                         genoma(N-i+1,k)=LI(k);
                         genoma(i,k)=LN(k);
                     end

                case 2
                    b=1;
                    for j=Lx+1:2*Lx
                        MI(b)=genoma(i,j);
                        MN(b)=genoma(N-i+1,j);
                        b=b+1;
                    end
                     a=1;
                     for k=Lx+1:2*Lx
                         genoma(N-i+1,k)=MI(a);
                         genoma(i,k)=MN(a);
                         a=a+1;
                     end                
                 otherwise
                     c=1;
                    for j=2*Lx+1:size(genoma,2)
                        RI(c)=genoma(i,j);
                        RN(c)=genoma(N-i+1,j);
                        c=c+1;
                    end
                    a=1;
                    for k=2*Lx+1:size(genoma,2)
                        genoma(N-i+1,k)=RI(a);
                        genoma(i,k)=RN(a);
                        a=a+1;
                    end

             end
        end
    end
end

for i=1:round(size(genP,1)/2)
    for j=1:round(size(genP,2))
       genP(i,j)=genoma(i,j);
    end
end
end

         
         
        