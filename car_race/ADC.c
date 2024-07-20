#define F_CPU 16000000UL
#define BAUD 9600
#include <avr/sfr_defs.h>
#include <util/delay.h>
#include "ADC.h"
#include "USART.h"
void ADC_init(){
	ADMUX |= (1<<REFS0);
	ADMUX |= (1<<ADLAR);
	ADCSRA |= (1<<ADPS2) | (1<<ADPS1)| (1<<ADPS0);
	ADCSRA |= (1<<ADEN);
}
uint8_t ADC_8bit_single(uint8_t sensor){
	ADMUX &= 0xf0;	
	ADMUX |= sensor;
	ADCSRA |= (1<<ADSC);
	loop_until_bit_is_clear(ADCSRA,ADSC);
	return ADCH;
}
void read_sensors(uint8_t* sensor_arr){
	for(uint8_t i = 0;i<SENSORS;i++){
		sensor_arr[i] = ADC_8bit_single(i);
	}
}