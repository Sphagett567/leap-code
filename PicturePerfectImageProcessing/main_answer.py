def denoise_image(pixels, height, width, n):
    discard = 0
    new_image = []
    for pic_row in range(0, height):
        temprow = []
        for pic_col in range(0, width):
            red_mean = 0
            blue_mean = 0
            green_mean = 0
            for win_row in range(0, n):
                for win_col in range(0, n):
                    top_valid = pic_row + win_row - n/2
                    side_valid = pic_col + win_col - n/2
                    if (((top_valid < 0) or (side_valid < 0)) or ((top_valid > height-1) or (side_valid > width - 1))):
                        discard = discard + 1
                    else:
                        red_mean += pixels[top_valid][side_valid].getRed()
                        blue_mean += pixels[top_valid][side_valid].getBlue()
                        green_mean += pixels[top_valid][side_valid].getGreen()
            temprow.append(red_mean / ((n*n) - discard))
            temprow.append(green_mean / ((n*n) - discard))
            temprow.append(blue_mean / ((n*n) - discard))
            discard = 0
        new_image.append(temprow)
    return new_image
