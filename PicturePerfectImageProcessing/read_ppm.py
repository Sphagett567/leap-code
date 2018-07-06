from RGB import *

def read_ppm(filename):
    f = open(filename, "r")
    content = f.readlines()
    cut = []
    for i in content:
        if (i[-1] == '\n'):
            cut.append(i[:-1])
        else:
            cut.append(i)
    pixels = []
    if (cut[0] == 'P3'):
        width, height = cut[1].split(" ")
        width = int(width)
        height = int(height)
        for i in range(3, len(cut)):
            inc = cut[i].split(" ");
            temp = []
            for j in range(0,(width*3) - 2,3):
                temp.append(RGB(int(inc[j]), int(inc[j+1]), int(inc[j+2])))
            pixels.append(temp)
        return pixels
    else:
        print "Incorrect encoding. Only P3 is permitted"
        return []
