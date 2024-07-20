#include "USART.h"

#define F_CPU 16000000UL
#define BAUD 9600
#define MAX_LEN (len_string+2)
#include <avr/sfr_defs.h>
#include <util/setbaud.h>
#include <avr/interrupt.h>
#include <util/delay.h>
#include <stdlib.h>
#include <stdio.h>
#include "motor.h"
#include "PID.h"
volatile uint8_t RECEIVING_PID_VARS = 0;
ISR(USART_RX_vect){
	received = UDR0;
	if(RECEIVING_PID_VARS){			
		rec_buf[buf_index] = received;
		buf_index++;
		if(buf_index == SIZE_OF_PID_VARS){
			RECEIVING_PID_VARS = 0;
		}
	}
	switch(received){
		case START_ROBOT:
			motor_direction(FORWARD);
			START = 1;
			break;
		case PAUSE_ROBOT:
			cli();
			motor_direction(STOP);
			_delay_ms(5000);
			motor_direction(FORWARD);
			sei();
			break;
		case STOP_ROBOT:
			START = 0;
			motor_direction(STOP);
			break;
		case SEND_PID_VARS:
			RECEIVING_PID_VARS = 1;
			break;
		default:
			break;
	}
}
void USART_init(){
	UBRR0H = UBRRH_VALUE;
	UBRR0L = UBRRL_VALUE;
	UCSR0C |= (1<<UCSZ01) | (1<<UCSZ00);
	UCSR0B |= (1<<TXEN0);
	UCSR0B |= (1<<RXEN0) | (1<<RXCIE0);
	for (int i = 0;i<SIZE_OF_PID_VARS;i++){
		rec_buf[i] = 0;
	}
	
	buf_index = 0;
}
void USART_send_byte(uint8_t byte){
	cli();
	loop_until_bit_is_set(UCSR0A, UDRE0);
	UDR0 = byte;
	sei();
}
void USART_send_string(char* message){
	volatile uint8_t i = 0;
	while (message[i]){
		USART_send_byte(message[i++]);
	}
}
void USART_send_signed_16bit(int16_t msg){
		USART_send_byte((msg >> 8) & 0xFF);
		USART_send_byte((msg) & 0xFF);
}
void USART_send_int_as_string(int16_t value){
	uint8_t len_string	= snprintf(NULL, 0, "%d", value);
	char *string = malloc(MAX_LEN);
	snprintf(string,MAX_LEN,"%d",value);
	USART_send_string(string);
	free(string);
}
void USART_send_float_as_string(double value){
	char *string = malloc(15);
	dtostrf(value,2,7,string);
	USART_send_string(string);
	free(string);
}
void USARt_send_realterm_newline(){
	USART_send_byte('A');
	USART_send_byte('B');

}
