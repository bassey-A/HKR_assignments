#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "functions.h"



int main() {

    srand(time(NULL));
    showMenu();
    //questions(practiceOrTest(), addAndSub());         It didn't work this way. Please tell me why it didn't
    pracOrTest = practiceOrTest();
    addOrSub = addAndSub();
    questions(pracOrTest, addOrSub);
    main();

    return 0;
}
