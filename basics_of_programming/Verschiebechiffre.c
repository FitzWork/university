#include <stdio.h>
#include <string.h>                                                                 //um l�nge des Strings einfach bestimmen zu k�nnen

char* encipher(char string[], int key) {                                            //Methode zur Verschl�sselung bekommt einen Satz und Schl�sselwert �bergeben
	for (int pointer = 0; pointer < strlen(string); pointer++) {                    //Wiederholt so oft wie der String lang ist
		if (string[pointer] == 32);                                                 //32 ist der Wert von einem Leerzeichen, welches beibehalten werden soll -> nicht soll passieren
		else {
			string[pointer] = string[pointer] + key;                                //sonst wird der Wert an dem Index um Schl�sselwert erh�ht
			if (string[pointer] > 90)string[pointer] = 64 + (string[pointer] - 90); //sollte es der Wert gr��er als der von Z ist, dann wird bei A wieder angefangen
		}
	}
	return string;
}

char* decipher(char string[], int key) {                                            //Wie Verschl�sselung, nur dass der datz entschl�sselt wird
	for (int pointer = 0; pointer < strlen(string); pointer++) {
		if (string[pointer] == 32);
		else {
			string[pointer] = string[pointer] - key;                                //dadurch muss es nicht um Schl�sselwert erh�ht sondern veringert werden
			if (string[pointer] < 65) string[pointer] = 91 - (65 - string[pointer]);//damit muss gepr�ft werden ob es den Wert von A unterschreitet wenn ja, wird ab Z weiter zur�ck gerechnet
		}
	}
	return string;
}

int main() {
	char Satz1[] = "YLHOH NDPHQ DOOPDHKOLFK CX GHU XHEHUCHXJXQJ HLQHQ JURVVHQ IHKOHU JHPDFKW CX KDEHQ DOV VLH YRQ GHQ EDHXPHQ KHUXQWHUJHNRPPHQ ZDUHQ XQG HLQLJH VDJWHQ VFKRQ GLH EDHXPH VHLHQ HLQ KROCZHJ JHZHVHQ GLH RCHDQH KDHWWH PDQ QLHPDOV YHUODVVHQ GXHUIHQ";
	int key = 3;
	printf("\nDer mit dem Schluesselwert %d zu uebersetzende Satz lautet:\n", key); //unverschl�sselt ausgeben um am Ende pr�fen zu k�nnen ob Verschl�sselung richtig funktioniert hat
	for (int pointer = 0; pointer < strlen(Satz1); pointer++)printf("%c", Satz1[pointer]);

	decipher(Satz1, key);                                                           //erst Verschl�sselung der Aufgabe entschl�sseln und ausgeben
	printf("\n\nSomit lautet der verschluesselte Satz tatsaechlich:\n");
	for (int pointer = 0; pointer < strlen(Satz1); pointer++) printf("%c", Satz1[pointer]);

	encipher(Satz1, key);                                                           //danach wieder verschl�sseln und ausgeben
	printf("\n\nDieser lautet verschluesselt wieder:\n");
	for (int pointer = 0; pointer < strlen(Satz1); pointer++) printf("%c", Satz1[pointer]);
	printf("\n");

	printf("\n\nUebersetze den Satz: \"TESTSATZ UEBERSETZEN LASSEN\"\nSchluesselwert: ");//Benutzereingabe testen
	scanf("%d", &key);
	char Satz2[] = "TESTSATZ UEBERSETZEN LASSEN";

	encipher(Satz2, key);
	printf("\nDieser lautet verschluesselt wieder:\n");
	for (int pointer = 0; pointer < strlen(Satz2); pointer++) printf("%c", Satz2[pointer]);
	printf("\n");

	decipher(Satz2, key);
	printf("\nSomit lautet der verschluesselte Satz tatsaechlich wieder:\n");
	for (int pointer = 0; pointer < strlen(Satz2); pointer++) printf("%c", Satz2[pointer]);

	printf("\n");
	return 0;
}
