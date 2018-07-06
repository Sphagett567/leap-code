from RGB import *
from read_ppm import *
from write_ppm import *

def denoise_image(pixels, height, width, n):
    return 0;
    # remove this return statement and begin to write your code

print "Enter the file you wish to edit: "
picture = str(raw_input())
print "Enter the file you want to output to: "
outfile = str(raw_input())
print "Enter the size of the sliding frame: "
n = int(raw_input())
print "Starting mean filter..."

pixels = read_ppm(picture)

new_image = denoise_image(pixels, 600, 600, n)

write_ppm(new_image, outfile)
        
    
