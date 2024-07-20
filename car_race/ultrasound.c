#ifndef F_CPU
	#define F_CPU 16000000UL
#endif

#include <avr/interrupt.h>
#include <util/delay.h>
#include "timers.h"
#include "ultrasound.h"
#include "USART.h"
#include "motor.h"
#define PRESCALER 64
#define CLOCKS_PER_MICRO (F_CPU / 1000000)
#define MICROS_PER_TCNT1 (PRESCALER / CLOCKS_PER_MICRO)
ISR(INT1_vect){
	if(PIND & (1 << PIND3)){
		TCNT1 = 0;
	}else{
		distance[LEFT_ULTRA] = ( (TCNT1 * MICROS_PER_TCNT1 * 17) / 100 );	/*Speed of sound, 17/100 = (340/2)*10^-3 = (Speed of sound / half of distance traveled) in mm*/
		ULTRASOUND_LEFT_READY = 1;
	}
}
ISR(PCINT0_vect){
	if(PINB & (1 << PINB3)){
		TCNT1 = 0;
	}else{
		distance[RIGHT_ULTRA] = ( (TCNT1 * MICROS_PER_TCNT1 * 17) / 100 );
		ULTRASOUND_LEFT_READY = 0;
	}
}
void ultrasound_init(){
	TIMER_init(ULTRASOUND);
	EXT_init();	
}
void EXT_init(){	
	EICRA |= (1<<ISC10);	
	EIMSK |= (1<<INT1);		
	PCICR |= (1<<PCIE0);	
	PCMSK0 |= (1<<PCINT3);
	DDRB |= (1<<PINB1);
	DDRB |= (1<<PINB2);
}
void send_trig(){
	if(!ULTRASOUND_LEFT_READY){
		PORTB &= ~(1<<PORTB1);
		_delay_us(2);
		PORTB |= (1<<PORTB1);
		_delay_us(10);
		PORTB &= ~(1<<PORTB1);
		}else if(ULTRASOUND_LEFT_READY){
		PORTB &= ~(1<<PORTB2);
		_delay_us(2);
		PORTB |= (1<<PORTB2);
		_delay_us(10);
		PORTB &= ~(1<<PORTB2);
	}
}
void obstacle_check(){
	if((distance[LEFT_ULTRA] < MIN_DISTANCE_OBSTACLE)){
		motor_direction(STOP);
	}else if((distance[RIGHT_ULTRA] < MIN_DISTANCE_OBSTACLE)){
		motor_direction(STOP);
	}else if(START){
		motor_direction(FORWARD);
	}else{
	}
}