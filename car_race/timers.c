#include "timers.h"
#include <avr/interrupt.h>

#define PRESCALER 64
void TIMER_init(uint8_t mode){
	if(mode == MOTOR_PWM){
		TCCR0A |= (1<<COM0A1) | (1<<COM0B1); 
		TCCR0A |= (1<<WGM01) | (1<<WGM00);
		TCCR0B |= (1<<CS00);
	}else if(mode == ULTRASOUND){
		TCCR1B |= (1<<CS11) | (1<<CS10);
	}else if(mode == ACCELEROMETER){
		//TCCR2A |= (1<<WGM21);
		TCCR2B |= (1<<CS22) | (1<<CS21) | (1<<CS20);
		TIMSK2 |= (1<<TOIE2);
	}
}