#ifndef USART_H_INCLUDED
#define USART_H_INCLUDED
#include <avr/io.h>
#define START_ROBOT 'A'
#define PAUSE_ROBOT 'B'
#define STOP_ROBOT  'C'
#define SEND_PID_VARS 'D'
volatile uint8_t received;
volatile uint8_t START;
volatile uint8_t buf_index;
#define SIZE_OF_PID_VARS 5
volatile uint8_t rec_buf[SIZE_OF_PID_VARS];
#define P_PRESCALE 100
#define I_PRESCALE 1000
#define D_PRESCALE 1
void USART_init();
void USART_send_byte(uint8_t);
void USART_send_string(char*);
void USART_send_signed_16bit(int16_t);
void USART_send_int_as_string(int16_t);
void USART_send_float_as_string(double);
void USARt_send_realterm_newline(void);
#endif