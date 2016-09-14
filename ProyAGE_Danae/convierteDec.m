function dec=convierteDec(x)
p=getParams;
 a=0;e=0;
 n=size(x,2);
 
 while n>=2  %n=1 es el bit de signo
     e=e+(2^a)*x(n);
     a=a+1;
     n=n-1;
 end
 if(x(1)==0)
    dec=e/(2^p(4));
 else
     dec=-(e/(2^p(4)));
 end
     
 
end
 
  
 

    