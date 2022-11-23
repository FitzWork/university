#include <stdio.h>
#include <stdlib.h>

struct node {
    int data, index;
    struct node *next;
};

struct node *auf, *zu, *helf;

struct node *mkNode(char val){
    struct node *node = NULL;
    if((node = malloc(sizeof (struct node))) != NULL){
        node->data = val;
        node->next = NULL;
        return node;
    }
    else
        {return NULL;}
}

void pushauf(char x){
    struct node *p = mkNode(x);
    p->next = auf;
    auf = p;
}

void pushzu(char x){
    struct node *p = mkNode(x);
    p->next = zu;
    zu = p;
}

void pushhelf(char x){
    struct node *p = mkNode(x);
    p->next = helf;
    helf = p;
}

void popauf(void){
    struct node *p = auf;
    auf = auf->next;
    free(p);
}

void popzu(void){
    struct node *p = zu;
    zu = zu->next;
    free(p);
}

void pophelf(void){
    struct node *p = helf;
    helf = helf->next;
    free(p);
}

int main(){
    int index;
    char eingabe [51];
    printf ("Bitte String eingeben (max. 50 Zeichen): ");
    gets (eingabe);
    pushauf('-');pushzu('-');

    for (index = 0; eingabe[index] != '\0'; index++){
        if (eingabe[index] == '(' || eingabe[index] == '{' || eingabe[index] == '[')
            {pushauf(eingabe[index]); auf->index = index;}
        if(eingabe[index] == ')'){
            if(auf->data == '(')
                {popauf();}
            else
                {pushzu(eingabe[index]); zu->index = index;}
        }
        if(eingabe[index] == '}'){
            if(auf->data == '{')
                {popauf();}
            else
                {pushzu(eingabe[index]); zu->index = index;}
        }
        if(eingabe[index] == ']'){
            if(auf->data == '[')
                {popauf();}
            else
                {pushzu(eingabe[index]); zu->index = index;}
        }
    }

    if(zu->data == '-' && auf->data == '-'){
        printf("\nAlle Klammersetzungen sind richtig!\n");
    }
    if(zu->data != '-'){
        printf("\nAlle markierten Klammern wurden nicht geoeffnet:\n%s\n", eingabe);
        while(zu->next!=NULL){
            pushhelf(zu->data);
            helf->index = zu->index;
            popzu();
        }
        for(int n=0;helf!=NULL;n++){
            if(n==helf->index) {printf("^"); pophelf();}
            else printf(" ");
        }
    }
    if(auf->data != '-'){
        printf("\nAlle markierten Klammern wurden nicht geschlossen:\n%s\n", eingabe);
        while(auf->next!=NULL){
            pushhelf(auf->data);
            helf->index = auf->index;
            popauf();
        }
        for(int n=0;helf!=NULL;n++){
            if(n==helf->index) {printf("^"); pophelf();}
            else printf(" ");
        }
    }

    return 0;
}
