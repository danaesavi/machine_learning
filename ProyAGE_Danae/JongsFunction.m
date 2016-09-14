function f=JongsFunction(x)
f=0;
for i=1:size(x,2)
    f=f+x(i)^2;
end
end
