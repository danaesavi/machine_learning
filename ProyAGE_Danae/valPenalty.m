function K_index=valPenalty(K) %se usa para generalizar a polinomios, está incompleta
K_index=zeros(4,1);
c=6;
k=1000000000;
for c=1:4 %4=grado del polinomio
    K_index(c)=K/c;
end
end