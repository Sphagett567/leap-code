def denoise_image(pixels, height, width, n):
    discard = 0
    new_image = []
    for pic_row in range(0, height):
        temprow = []
        for pic_col in range(0, width):
            red_mean = 0
	# Need variables for blue and green mean values!

            for win_row in range(0, n):
                for win_col in range(0, n):
                    top_valid = pic_row + win_row - n/2
                    side_valid = pic_col + win_col - n/2
                    if (((top_valid < 0) or (side_valid < 0)) or ((top_valid > height-1) or (side_valid > width - 1))):
                        discard = discard + 1
                    else:
                        red_mean += pixels[top_valid][side_valid].getRed()
			# Need to calculate the green and blue values!
            temprow.append(red_mean / ((n*n) - discard))
	    # Need to add the blue and green values to the row too!
            discard = 0
        new_image.append(temprow)
    return new_image
