a=[1,2,3,4,5]

def invierteLista(lista):
    b=[0 for x in range(0,len(lista))]
    i=len(lista)-1
    for x in range (0,len(lista)):
      b[x]=lista[i]
      i=i-1
    lista=b
    return lista

print invierteLista(a)
