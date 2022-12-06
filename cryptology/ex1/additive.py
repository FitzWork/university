def readChars(text):
    # list all different chars in an array
    chars = []
    for i in range(len(text)):
        if text[i] not in chars:
            chars.append(text[i]) 
    return chars


# counts how often a char appears and save the highest count
def analyse(text, chars):
    maxCount = text.count(chars[0])
    key = 0
    for index in range(len(chars)):
        if text.count(chars[index]) > maxCount:
            maxCount = text.count(chars[index])
            key = index

    print(chars[key] + " is the most printed symbol (" + str(maxCount) + " times) and must be the SPC in ASCI")
    print("In that case must be the key: " + str(ord(chars[key]) - 32))
    key = ord(chars[key]) - 32
    return key


# decrypt the text, change the key for the letter if needed and print the final text
def decrypt(text, d):
    writeData = ""
    for index in range(len(text)):
        if ord(text[index]) - d < 0:
            writeData = writeData + (chr(128 + (ord(text[index]) - d)))
        elif ord(text[index]) - d > 127:
            writeData = writeData + (chr(0 + (ord(text[index]) - d - 126)))
        else:
            writeData = writeData + (chr(ord(text[index]) - d))
    print()
    print(writeData)
    return writeData


# check this 
def encrypt(text, e, lorem):
    writeData = ""
    for index in range(len(text)):
        if ord(text[index]) + e < 0:
            writeData = writeData + (chr((ord(text[index]) + e) + 128))
        elif ord(text[index]) + e >= 127:
            # nicht ganz sicher weshalb hier 128 stehen muss, da man ja so auf -1
            # kommen könnte? 
            writeData = writeData + (chr((ord(text[index]) + e) - 128))
        else:
            writeData = writeData + (chr(ord(text[index]) + e))

    print("\n" + writeData)
    print(writeData == lorem)
    # decrypt(writeData, e)



with open("LoremIpsumEncrypted.txt", "r") as c:
        lorem = c.read()

print(lorem)
chars = readChars(lorem)
key = analyse(lorem, chars)
ret = decrypt(lorem, key)    
encrypt(ret, key, lorem)
