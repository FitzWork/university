#include <stdio.h>
#include <stdlib.h>

struct dnode{
    int data; struct dnode *next, *prev;
};

struct dnode *head, *tail;

struct dnode *mkNode(int val){
    struct dnode *node = NULL;
    if((node = malloc(sizeof (struct dnode))) != NULL){
        node->data = val; node->next=node->prev = NULL;
        return node;}
    else {return NULL;}                     //Falls Erzeugung nicht möglich ist
}

void printList(void){                       //Ausgabe von head nach tail (zu letzt hinzugefügtes Element als erstes)
    struct dnode *tmp = head;
    printf("(");
    while(tmp != NULL){
        printf(" %d", tmp->data);
        tmp = tmp->next;
    }
    printf(" )\n");
}

void printListback(void){                   //Ausgabe von tail nach head (zu erst hinzugefügtes Elemten als erstes a)
    struct dnode *tmp = tail;
    printf("(");
    while(tmp != NULL){
        printf(" %d", tmp->data);
        tmp = tmp->prev;
    }
    printf(" )\n");
}

void insertFirst (int val) {                // Einfügen am Listenanfang
    struct dnode *p = mkNode(val);
    if(head==NULL){
        head=p;
        tail=p;                             //zu erst hinzugefügtes Element ist letztes der Liste
    }
    else{
        head->prev = p;
        p->next = head;                     //der Zeiger des neuen Elements zeigt mit zeiger auf den Zeiger des nächsten Elements
        head = p;
    }
}

void delete (int val) {                     // Löschen eines Werts
    struct dnode *p = head, *prev = NULL;
    while (p != NULL && p->data != val) {
        prev = p; p = p->next;
    }                                       // p == NULL || p->data == val
    if (p != NULL) {                        // p->data == val
        if (p == head) {
            head = p->next;
            head->prev = NULL;
        }
        else {
            prev->next = p->next;
            if(p->next == NULL){            //falls p tail ist
                prev->next = NULL;          //prev-> next zeigt auf NULL
                tail = prev;                //prev wird neues tail
            }
            else p->next->prev = p->prev;   //der Zeiger auf das vorherige Element des Elementes nach p soll auf prev zeigen
        }
        free(p);
    }
}

int main(void){                                 //Aufruf von Aufgabenstellung
    insertFirst(2);
    insertFirst(3);
    insertFirst(5);
    insertFirst(7);
    insertFirst(11);
    insertFirst(13);
    insertFirst(17);
    insertFirst(19);
    insertFirst(23);
    insertFirst(29);
    insertFirst(31);
    insertFirst(37);
    insertFirst(41);
    insertFirst(43);
    insertFirst(47);
    insertFirst(53);
    insertFirst(59);
    insertFirst(61);
    insertFirst(67);
    insertFirst(71);
    insertFirst(73);
    insertFirst(79);
    insertFirst(83);
    insertFirst(89);
    insertFirst(97);
    printf("(ueber ->next) ");
    printList();
    printf("(ueber ->prev) ");
    printListback();

    printf("\na) Welche Zahl soll entfernt werden?");
    int n;
    scanf("%d",&n);
    delete(n);
    printList();                                //je nach dem welche Ausgabe erwünscht ist ...
    printListback();

    printf("\nb) Welche Zahl soll hinzugefuegt werden?");
    scanf("%d",&n);
    insertFirst(n);
    printList();
    printListback();

    return 0;
}
