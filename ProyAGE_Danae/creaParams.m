function creaParams

[params]=textread('AGParamsOriginal.txt','%f',11);
disp(params);
prompt = '�Quieres cambiar alg�n valor?: 1--> s�/2-->no';
while  (input(prompt)== 1)
    preg='Elige una opci�n:\n 2 N�mero de Individuos \n 3 Bits enteros \n 4 Bits decimales \n 6 Pc \n 7 Pm \n 9 G(n�mero de geeraciones)';
    numOp=input(preg);
    valor='Escribe el nuevo valor';
    %modify
    val=input(valor);
    params(numOp)=val;
    disp(params);
    prompt = '�Quieres cambiar alg�n valor?: 1--> s�/2-->no';
end
%updateParams
fileID=fopen('AGParams.txt','w');
 fprintf(fileID,'%f \n',params);
 type('AGParams.txt');
 fclose(fileID);
 
end
