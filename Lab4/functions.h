//
// Created by basee on 2021-03-29.
//

#ifndef UNTITLED9_FUNCTIONS_H
#define UNTITLED9_FUNCTIONS_H

char name[50];
int  choice, pracOrTest, addOrSub;
int check = 0;
const int MaxValue = 100, Practice = 10, Test = 15;
void questions(int x, int y);
int addAndSub();
void showMenu();
void responses(int x);
int randNumGen(int x);
typedef enum {sub, add} bool;
char *boolToString(bool add);
bool getSign(int y);
int practiceOrTest();
void printResult(int firstValue[], int secondValue[], int correctAnswer[], int answer[], int sign[]);
char congratulate[8][50] = {"Good job!", "Very good!", "Excellent!", "Nice work!", "Well done!", "Great!",
                           "Keep up the good work!", "That's the right answer!"};
char motivate[8][50] = {"No. Please try again. Enter your answer: ", "Wrong. Try once again: ",
                        "Don't give up! Try again: ", "No. Keep trying: ", "That's close! Retry: ", "Check once more: ",
                        "Almost perfect! Retry: ", "Just a step away. Retry: "};


char *boolToString(bool add){return add ? "+" : "-";}   //replaces the boolean values with signs when printing

void showMenu(){             //Gets the user's name and prints a welcome message
    do{
        if (check > 0){break;}
        check++;
        printf("Enter your name:   ");
        fgets(name, sizeof(name), stdin );
        for (int i = 0; i < sizeof(name); i++) {
            if (name[i] == '\n') {
                name[i] = '\0';
                break;
            }
        }
        printf("\nWelcome %s!\n", name);
    } while (check < 1);
}

int practiceOrTest() {
    printf("\nYou can choose:\n"
           "\t1.  do a practice\n"
           "\t2.  complete a test\n"
           "\t3.  quit the program\n\n"
           "Enter your choice:  ");

    do{
        (scanf("%d", &choice));
        if (choice != 1 && choice != 2 && choice != 3){
            printf("Invalid Selection! Please try again\n");
        }
    } while (choice != 1 && choice != 2 && choice != 3);
    switch (choice) {
        case 1: return 10;      //The default number of questions for a practice
        case 2: return 15;      //The default number of questions for a test
        case 3: exit(0);
    }
    //addAndSub();
}

void exit(int x);       //quits the program

int addAndSub(){
    printf("\n\t1.    additions\n"
           "\t2.    subtractions\n"
           "\t3.    additions and subtractions\n");

    do{
        (scanf("%d", &choice));
        if (choice != 1 && choice != 2 && choice != 3){
            printf("Invalid Selection! Please try again\n");
        }
    } while (choice != 1 && choice != 2 && choice != 3);
    switch (choice) {
        case 1: return 1;      //1 means addition
        case 2: return 2;      //2 means subtraction
        case 3: return 3;      //3 means addition and subtraction
    }
}

void questions(int x, int y){       //x: Practice or Test  y: addition or subtraction
    int firstValue[x], secondValue[x], correctAnswer[x], answer[x], sign[x];
    int printChecker = x;

    printf("Now you will be given %d questions to solve:\n", x);
    for (int i = 0; i < x; i++) {
        sign[i] = getSign(y);
        firstValue[i] = randNumGen(MaxValue);
        if (sign[i] == add) {
            secondValue[i] = randNumGen(MaxValue);
            correctAnswer[i] = firstValue[i] + secondValue[i];
            printf("\n\t%d.   %d + %d = \n", (i + 1), firstValue[i], secondValue[i]);
        } else if (sign[i] == sub){
            secondValue[i] = randNumGen(firstValue[i]);
            correctAnswer[i] = firstValue[i] - secondValue[i];
            printf("\n\t%d.   %d - %d = \n", (i + 1), firstValue[i], secondValue[i]);
        }
        printf("Enter your answer:");
        scanf("%d", &answer[i]);
        if (printChecker == Practice){
            if(correctAnswer[i] == answer[i]){
                responses(1);
            }else{
                do{
                    responses(0);
                    scanf("%d", &answer[i]);
                    if (correctAnswer[i] == answer[i]){responses(1);}
                }while (correctAnswer[i] != answer[i]);
            }
        }
    }
    if (printChecker == Test){
        printResult(firstValue, secondValue, correctAnswer, answer, sign);
    }
    printf("\n\nWelcome back %s", name);

}

bool getSign(int y){        //determines if the question will be addition or subtraction
    if (y == 3){
        y = randNumGen(2);
    }
    if (y == 1){
        return add;
    }else if (y == 2){
        return sub;
    }
}

void responses(int x){      //displays messages after the user answers a question during a practice session
    if (x == 1){
        printf("%s\n", congratulate[randNumGen(8)]);
    }else if (x == 0){
        printf("%s", motivate[randNumGen(8)]);
    }
}

int randNumGen(int x){      //generates random numbers
    return rand() % x + 1;
}

void printResult(int firstValue[], int secondValue[], int correctAnswer[], int answer[], int sign[]){
    float result;
    for ( int i = 0; i < Test; i++){
        if (correctAnswer[i] == answer[i]){
            result++;
        }
    }

    result = ((result / Test) * 100);
    printf("\n\nYour test result is %d%%\n", (int)result);
    printf("Detailed questions and answers:\n\n");
    printf("Nr       Question        Correct Answer      Your Answer\n");
    for (int i = 0; i < Test; i++){
        printf("%2d\t %3d %c %3d\t\t %3d\t\t%3d\n", (i + 1), firstValue[i], *boolToString(sign[i]),
               secondValue[i],correctAnswer[i],answer[i]);
    }
}

#endif //UNTITLED9_FUNCTIONS_H
