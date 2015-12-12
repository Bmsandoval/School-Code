ARRAY 	.INT	0
	.INT	0
	.INT	0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
        .INT    0
CNT	.INT	0
ZERO	.INT	0
ONE	.INT	1
UNF	.INT	-42
OVF	.INT	42
START	.INT	0
LKARY	.INT	0
F	.BYT	70
a       .BYT    97
c       .BYT	99
t       .BYT	116
o       .BYT	111
r       .BYT	114
i       .BYT	105
l       .BYT	108
f       .BYT	102
s       .BYT	115
SPC	.BYT	32
NL	.BYT	13
COMMA	.BYT	44
	JMP	WHL
OVFLW   LDR	R3	OVF 		; stack overflow occured
	TRP	1
	LDB	R3	NL
	TRP	3
	JMP	FIN
UNFLW	LDR	R3	UNF 		; stack underflow occured
	TRP	1
	LDB	R3	NL
	TRP	3
	JMP	FIN
FCTR    MOV     R7      SP              ; ==========================================================================================> Func FCTR (factor)
        CMP     R7      SL
        BLT     R7      OVFLW	
	MOV	R7	SP
	LDR	R7	R7
	MOV	R6	R7
	BNZ	R6	FCTMO		; if value is not zero, branch and continue calling FCTR
	LDR	R7	ONE		; if value IS zero, return 1
	STR	R0	SP
        MOV     SP      FP              ; pop FCTR off the stack
        MOV     R0      FP
        ADI     R0      -8
        LDR     R0      R0
        MOV     FP      R0
        MOV     R0      SP
        CMP     R0      SB
        BGT     R0      UNFLW
        MOV     R0      SP
        ADI     R0      -4
        LDR     R0      R0
	MOV	R6	SP
	ADI	R6	-4
        STR     R7      R6		; store return value
        MOV     PC      R0
FCTMO	ADI	R7	-1		; decrement value to send back to FCTR again
        MOV     R0      SP              ; begin calling FCTR, check for stack overflow
        ADI     R0      -12             ; FCTR takes one parameter
        CMP     R0      SL
        BLT     R0      OVFLW
        MOV     R0      SP
        ADI     R0      -12
        STR     R7      R0
        MOV     R0      SP
        MOV     R1      PC
        ADI     R1      108
        ADI     SP      -4
        STR     R1      SP              ; STORE RETURN ADDRESS
        ADI     SP      -4
        STR     FP      SP              ; STORE NULL VAL FOR PFP
        MOV     FP      SP
        ADI     FP      8
	ADI	SP	-4
        JMP     FCTR                    ; Begin function FCTR
        MOV     R0      SP              ; check for stack underflow
        MOV     R1      SB
        CMP     R1      R0
	BLT	R1	UNFLW
	MOV	R1	SP		; retrieve returned value
	ADI	R1	-4
	LDR	R1	R1
	MOV	R2	SP		; retrieve start-of-function value
	LDR	R2	R2
	MUL	R1	R2		; multiply these two values
	MOV	SP	FP		; pop FCTR off the stack
	MOV	R0	SP
	ADI	R0	-8
	LDR	R0	R0
	MOV	FP	R0
	MOV	R0	SP
	CMP 	R0	SB
	BGT	R0	UNFLW
	MOV	R0	SP
	ADI	R0	-4
	LDR	R0	R0
	MOV	R7	SP
	ADI	R7	-4
	STR	R1	R7
	MOV	PC	R0		; end of function FCTR
WHL	TRP	2			; request input from user
	BRZ	R3	ENWL		; if input == 0, break out of loop
	LDA	R1	ARRAY		; store input into ARRAY[CNT]
	LDR	R2	CNT
	LDR	R6	ZERO
	ADI	R6	4
	MUL	R2	R6
	ADD	R1	R2
	STR	R3	R1		
	LDR	R2	CNT
	ADI	R2	1		; increment CNT
	STR	R2	CNT
	MOV	R0	SP 		; begin calling FCTR, check for stack overflow
	ADI	R0	-12		; FCTR takes one parameter
	CMP	R0	SL
	BLT	R0	OVFLW
	MOV	R0	SP
	ADI	R0	-12
	STR	R3	R0
	MOV	R0	SP
	MOV	R1	PC
	ADI	R1	108
	ADI 	SP	-4		
	STR	R1	SP		; STORE RETURN ADDRESS
	ADI	SP	-4
	STR 	FP	SP		; STORE NULL VAL FOR PFP
	ADI	SP	-4
	MOV	FP	SP
	ADI	FP	12
	JMP	FCTR			; Begin function FCTR
	MOV	R0	SP		; check for stack underflow
	MOV	R1	SB
	CMP	R1	R0
	BLT	R1	UNFLW
	ADI	R0	-4		; load return value into R0
	LDR	R0	R0
	LDA	R4	ARRAY		; load user input into R4
	LDR	R3	CNT
	LDR	R6	ZERO
	ADI	R6	4
	MUL	R3	R6
	ADD	R4	R3
	ADI	R4	-4
	LDR	R4	R4
	LDA	R2	ARRAY		; store returned value into ARRAY[CNT]
	ADD	R2	R3
	STR	R0	R2
	LDR	R2	CNT
	ADI	R2	1
	STR	R2	CNT
	LDB	R3	F		; printf("Factorial of X is Y")
	TRP	3
	LDB	R3	a
	TRP	3
        LDB     R3      c
        TRP     3
        LDB     R3      t
        TRP     3
        LDB     R3      o
        TRP     3
        LDB     R3      r
        TRP     3
        LDB     R3      i
        TRP     3
        LDB     R3      a
        TRP     3
        LDB     R3      l
        TRP     3
        LDB     R3      SPC
        TRP     3
        LDB     R3      o
        TRP     3
        LDB     R3      f
        TRP     3
        LDB     R3      SPC
        TRP     3
        MOV     R3      R4
        TRP     1
        LDB     R3      SPC
        TRP     3
        LDB     R3      i
        TRP     3
        LDB     R3      s
        TRP     3
        LDB     R3      SPC
        TRP     3
        MOV     R3      R0
        TRP     1
        LDB     R3      NL
        TRP     3
	JMP	WHL			; loop back to beginning of WHL
ENWL	LDR	R0	ZERO
	LDR	R1	CNT
	ADI	R1	-1
	ADI	R0	4
	MUL	R1	R0
	LDR	R0	ZERO
PARSE	LDA	R3	ARRAY		; PRINT ARRAY[R0] AND ARRAY[R1] VAL, VAL, VAL, VAL...
	ADD	R3	R0
	LDR	R3	R3
	TRP	1
	LDB	R3	COMMA
	TRP	3
	LDB	R3	SPC
	TRP	3
	LDA	R3	ARRAY
	ADD	R3	R1
	LDR	R3	R3
	TRP	1
	ADI	R0	4
	ADI	R1	-4
	MOV	R2	R1
	CMP	R2	R0
	BLT	R2	ENDPS
        LDB     R3      COMMA
        TRP     3
        LDB     R3      SPC
        TRP     3	
	JMP	PARSE
ENDPS	LDB	R3	NL
	TRP	3
	LCK	START
MNWL	TRP	2
	BRZ	R3	ENMNWL
	RUN	R2	THREAD		;main calls new threads in a loop, jumping to THREAD
	JMP	MNWL
ENMNWL	LDR	R3	ZERO
	STR	R3	CNT
	ULK	START
	BLK
        LDR     R0      ZERO
        LDR     R1      CNT
        ADI     R1      -1
        ADI     R0      4
        MUL     R1      R0
        LDR     R0      ZERO
MNPRS	LDA     R3      ARRAY           ; PRINT ARRAY[R0] AND ARRAY[R1] VAL, VAL, VAL, VAL...
        ADD     R3      R0
        LDR     R3      R3
        TRP     1
        LDB     R3      COMMA
        TRP     3
        LDB     R3      SPC
        TRP     3
        LDA     R3      ARRAY
        ADD     R3      R1
        LDR     R3      R3
        TRP     1
        ADI     R0      4
        ADI     R1      -4
        MOV     R2      R1
        CMP     R2      R0
        BLT     R2      ENDMN
        LDB     R3      COMMA
        TRP     3
        LDB     R3      SPC
        TRP     3
        JMP     MNPRS
ENDMN   LDB     R3      NL
        TRP     3
	JMP	FIN
THREAD	LCK	START
	ULK	START
	MOV	R4	R3
        MOV     R0      SP              ; begin calling FCTR, check for stack overflow
        ADI     R0      -12             ; FCTR takes one parameter
        CMP     R0      SL
        BLT     R0      OVFLW
        MOV     R0      SP
        ADI     R0      -12
        STR     R3      R0
        MOV     R0      SP
        MOV     R1      PC
        ADI     R1      108
        ADI     SP      -4
        STR     R1      SP              ; STORE RETURN ADDRESS
        ADI     SP      -4
        STR     FP      SP              ; STORE NULL VAL FOR PFP
        ADI     SP      -4
        MOV     FP      SP
        ADI     FP      12
        JMP     FCTR                    ; Begin function FCTR
        MOV     R0      SP              ; check for stack underflow
        MOV     R1      SB
        CMP     R1      R0
        BLT     R1      UNFLW
        ADI     R0      -4              ; load return value into R0
        LDR     R0      R0
	MOV	R3	R4
        LDB     R3      F               ; printf("Factorial of X is Y")
        TRP     3
        LDB     R3      a
        TRP     3
        LDB     R3      c
        TRP     3
        LDB     R3      t
        TRP     3
        LDB     R3      o
        TRP     3
        LDB     R3      r
        TRP     3
        LDB     R3      i
        TRP     3
        LDB     R3      a
        TRP     3
        LDB     R3      l
        TRP     3
        LDB     R3      SPC
        TRP     3
        LDB     R3      o
        TRP     3
        LDB     R3      f
        TRP     3
        LDB     R3      SPC
        TRP     3
        MOV     R3      R4
        TRP     1
        LDB     R3      SPC
        TRP     3
        LDB     R3      i
        TRP     3
        LDB     R3      s
        TRP     3
        LDB     R3      SPC
        TRP     3
        MOV     R3      R0
        TRP     1
        LDB     R3      NL
        TRP     3
	LCK	LKARY
	LDR	R2	CNT
	LDR	R1	ZERO
	ADI	R1	4
	MUL	R2	R1
	LDA	R1	ARRAY
	ADD	R1	R2
	STR	R4	R1
	ADI	R1	4
	STR	R0	R1
        LDR     R2      CNT
        ADI     R2      2
        STR     R2      CNT
	ULK	LKARY
        END
FIN	TRP	0			; end program
