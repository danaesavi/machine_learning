function genomaCruza=muta(genomaCruza,B2M,N_2,L)


%Mutación de los primeros N
for i=1:round(B2M)
    b=round(N_2);
    nInd = round((b-1)*rand(1,1) + 1); %individuo a mutar
    nBit= round((L-1)*rand(1,1) + 1);

    

if(genomaCruza(nInd,nBit)==0)   %mBit
    genomaCruza(nInd,nBit)=1;
else
    genomaCruza(nInd,nBit)=0;
end

end

end


