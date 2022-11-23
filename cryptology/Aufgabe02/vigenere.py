def vigenere(cryptotext: str):
    print(len(cryptotext))
    häufigkeit = []
    for i in range(len(cryptotext) - 1):
        print(i)
        if i + 1 != len(cryptotext) - 2:
            for j in range(i + 2, len(cryptotext) - 1):
                if [cryptotext[j], cryptotext[j + 1]] == [cryptotext[i], cryptotext[i + 1]] and [cryptotext[i], cryptotext[i + 1], int] not in häufigkeit:
                    häufigkeit.__add__([cryptotext[i], [cryptotext], 1])
                else:
                    for position in range(len(häufigkeit)):
                        if [cryptotext[i], cryptotext[i + 1], int] == häufigkeit[position]:
                            häufigkeit[position][2] += 1
    print(häufigkeit)


with open("Lorem2.txt", "r") as c:
    lorem = c.read()

vigenere(lorem)
