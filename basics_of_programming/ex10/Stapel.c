#include <stdio.h>
#include <stdlib.h>

struct node {                           //PP 14 | Folie 5
    int data;
    struct node *next;
};

struct node *top;                       //PP 14 | Folie 23

struct node *mkNode(char val){          //PP 14 | Folie 5
    struct node *node = NULL;
    if((node = malloc(sizeof (struct node))) != NULL){
        node->data = val;
        node->next = NULL;
        return node;
    }
    else
        {return NULL;}
}

void push(char x){                      //PP 14 | Folie 23
    struct node *p = mkNode(x);
    p->next = top;
    top = p;
}

void pop(void){                         //PP 14 | Folie 23  wurde zu void Methode (Rückgabe nicht nötig)
    struct node *p = top;                //if-Schleife und alles was mit result zutun hat wurde entfernt
    top = top->next;
    free(p);
}

int main(){
    int index, koffen=0;                //Index wird nicht nur für for-Schleife benötigt | koffen ist Anzahl der geöffneten Klammern, wenn am Ende nicht 0 --> fehlerhafte Eingabe
    char eingabe [51];                  //bei Versuch Char-Array dynamisch zu machen sind Fehler aufgetaucht --> begrenzt
    printf ("Bitte String eingeben (max. 50 Zeichen): ");
    gets (eingabe);
    push(0);                            //0 wird als erster Wert in Stapel eingefügt, weil sonst Überprüfen des ersten vom Benutzer eingegebenen Wertes fehlschlägt

    for (index = 0; eingabe[index] != '\0'; index++){       //(hätte auch in separate Methode ausgelagert werden können)
        if (eingabe[index] == '(' || eingabe[index] == '{' || eingabe[index] == '[')
            {push(eingabe[index]); koffen++;}
        if(eingabe[index] == ')'){      //sollten die Klammern geschlossen werden wird überprüft ob der letzte eingetragene Wert die jeweilige Schließen-Klammer ist
            if(top->data == '(')
                {pop();koffen--;}
            else
                {koffen = -1; break;}   //falls nicht wird abgebrochen | koffen muss Wert außer 0 bekommen weil sonst Ausgabe fehlschlägt
        }
        if(eingabe[index] == '}'){
            if(top->data == '{')
                {pop(); koffen--;}
            else
                {koffen = -1; break;}
        }
        if(eingabe[index] == ']'){
            if(top->data == '[')
                {pop(); koffen--;}
            else
                {koffen = -1; break;}
        }
    }

    if(koffen != 0) {                   //falls noch es noch offene Klammern gibt wird Fehler ausgegeben und die Stelle als Fehler angezeigt ab der ein Fehler vorliegt
        printf("\nFehlerhafte Klammersetzung ab:");
        printf("\n%s\n",eingabe);
        for(int i=0;i<index;i++){
            printf(" ");
        }
        printf("^\n");
    }
    if(koffen == 0)printf("\nAlle Klammern stehen in der richtigen Reihenfolge.\n");

    return 0;
}
