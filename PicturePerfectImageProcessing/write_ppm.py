def write_ppm(new_image, outfile):
    o = open(outfile, "w")
    beginning = ["P3", "600 600", "255"]
    for i in range(0,3):
        o.write(beginning[i] + "\n")
    for i in range(0, len(new_image)):
        for j in range(0, len(new_image[0])):
            o.write(str(new_image[i][j]))
            if (j < len(new_image[0]) - 1):
                o.write(" ")
        o.write('\n')
    print "Image filtering done! Open your file to see the results"
