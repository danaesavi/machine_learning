function [N_2,Nx2,genoma,fitness,Norm,L,L_2,B2M]=calcParams
p=getParams;
v=4; %n=4
N=p(2);
N_2=N/2;				% La mitad del número de individuos
Nx2=N*2;				% El doble de los individuos
fitness= zeros(Nx2,1);	% Arreglo de double con Nx2 elementos
Norm=2^p(4);				% Valor para convertir entero a real
L=v*(1+p(3)+p(4));% Longitud del individuo en bits
genoma = zeros(Nx2,L);	% Arreglo de int con Nx2 filas y L col
L_2=L/2;				% Longitud del anillo
B2M=N*L*p(7);				% Número esperado de bits a mutar

end