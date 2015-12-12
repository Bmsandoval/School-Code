SIZE	.INT	7
ONE		.INT	1
CNT		.INT 	0
TENTH	.INT	0
ZERO	.INT	0
DATA	.INT	0
FLAG	.INT	0
OPDV	.INT	0
UNF 	.INT	-42
OVF 	.INT	42
NEG 	.INT 	-1
TEN 	.INT 	10
PLS		.BYT	43
0		.BYT	48
1		.BYT	49
2		.BYT	50
3		.BYT	51
4		.BYT	52
5		.BYT	53
6		.BYT	54
7		.BYT	55
8		.BYT	56
9		.BYT	57
NL		.BYT	10 		
C 		.BYT	0
		.BYT	0
		.BYT	0
		.BYT	0
		.BYT	0
		.BYT	0
		.BYT	0
O 		.BYT	79
p 		.BYT	112
d 		.BYT 	100
N 		.BYT	78
B 		.BYT	66
g 		.BYT 	103
i 		.BYT	105
s 		.BYT	115
n 		.BYT	110
o 		.BYT	111
t 		.BYT	116
a 		.BYT	97
u 		.BYT	117	
m 		.BYT	109
b 		.BYT	98
e 		.BYT	101
r 		.BYT	114
j 		.BYT	106
SPC 	.BYT	32
@ 		.BYT	64
+		.BYT	43
-		.BYT	45
		JMP		MAIN				; ==========================================================================================> program begins
OVFLW   LDR		R3		OVF 		; stack overflow occured
		TRP		1
		LDB		R3		NL
		TRP		3
		JMP		FIN
UNFLW	LDR		R3		UVF 		; stack underflow occured
		TRP		1
		LDB		R3		NL
		TRP		3
		JMP		FIN
OPD 	MOV		R7		SP 			; ==========================================================================================> Func OPD (opd)
		ADI		R7		-4			; only one local int required
		CMP 	R7		SL
		BLT		R7		OVFLW
		ADI		SP 		-4			; allocate space for local var t
		LDR		R7		ZERO
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		ADI		R6		4			; load j from memory
		LDB		R6		R6			
IF0		MOV		R5		R6
		LDB		R7		0			; compare j to 0
		CMP		R5		R7
		BNZ		R5		IF1			; if different, try the next
		LDR		R7		ZERO
		ADI		R7		0
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		JMP		ENF1
IF1		MOV		R5		R6
		LDB		R7		1			; compare j to 1
		CMP		R5		R7
		BNZ		R5		IF2			; if different, try the next
		LDR		R7		ZERO
		ADI		R7		1
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		JMP		ENF1
IF2		MOV		R5		R6
		LDB		R7		2			; compare j to 2
		CMP		R5		R7
		BNZ		R5		IF3			; if different, try the next
		LDR		R7		ZERO
		ADI		R7		2
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		JMP		ENF1
IF3		MOV		R5		R6
		LDB		R7		3			; compare j to 3
		CMP		R5		R7
		BNZ		R5		IF4			; if different, try the next
		LDR		R7		ZERO
		ADI		R7		3
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		JMP		ENF1
IF4		MOV		R5		R6
		LDB		R7		4			; compare j to 4
		CMP		R5		R7
		BNZ		R5		IF5			; if different, try the next
		LDR		R7		ZERO
		ADI		R7		4
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		JMP		ENF1
IF5		MOV		R5		R6
		LDB		R7		5			; compare j to 5
		CMP		R5		R7
		BNZ		R5		IF6			; if different, try the next
		LDR		R7		ZERO
		ADI		R7		5
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		JMP		ENF1
IF6		MOV		R5		R6
		LDB		R7		6			; compare j to 6
		CMP		R5		R7
		BNZ		R5		IF7			; if different, try the next
		LDR		R7		ZERO
		ADI		R7		6
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		JMP		ENF1
IF7		MOV		R5		R6
		LDB		R7		7			; compare j to 7
		CMP		R5		R7
		BNZ		R5		IF8			; if different, try the next
		LDR		R7		ZERO
		ADI		R7		7
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		JMP		ENF1
IF8		MOV		R5		R6
		LDB		R7		8			; compare j to 8
		CMP		R5		R7
		BNZ		R5		IF9			; if different, try the next
		LDR		R7		ZERO
		ADI		R7		8
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		JMP		ENF1
IF9		MOV		R5		R6
		LDB		R7		9			; compare j to 9
		CMP		R5		R7
		BNZ		R5		EL			; if different, try the next
		LDR		R7		ZERO
		ADI		R7		9
		MOV		R6		FP
		ADI		R6		-18
		STR 	R7		R6
		JMP		ENF1
EL 		MOV		R3		R6			; printf("%c is not a number\n", j)
		TRP		3
		LDB		R3		SPC
		TRP		3
		LDB		R3		i
		TRP		3
		LDB		R3		s
		TRP		3
		LDB		R3		SPC
		TRP		3
		LDB		R3		n
		TRP		3
		LDB		R3		o
		TRP		3
		LDB		R3		t
		TRP		3
		LDB		R3		SPC
		TRP		3
		LDB		R3		a
		TRP		3
		LDB		R3		SPC
		TRP		3
		LDB		R3		n
		TRP		3
		LDB		R3		u
		TRP		3
		LDB		R3		m
		TRP		3
		LDB		R3		b
		TRP		3
		LDB		R3		e
		TRP		3
		LDB		R3		r
		TRP		3
		LDB		R3		NL
		TRP		3
		LDR 	R7		ONE
		STR 	R7 		FLAG
ENF1	LDR 	R7		FLAG
		LDR 	R6		ONE
		CMP 	R6		R7
		BRZ		R6		ENF2
		MOV		R7		FP			; this will execute if j was a number
		ADI		R7		-9
		LDB		R6		R7
		LDB		R7		PLS
		CMP 	R6		R7
		BNZ		R6		NPLS
		MOV		R7		FP
		ADI		R7		-18
		LDR		R7		R7
		MOV		R6		FP
		ADI		R6		-13
		LDR		R6		R6
		MUL		R6		R7
		LDR		R7		OPDV
		ADD 	R6		R7
		STR 	R6		OPDV
		JMP		ENF2
NPLS	MOV		R7		FP
		ADI		R7		-18
		LDR		R7		R7
		MOV		R6		FP
		ADI		R6		-13
		LDR		R6		R6
		MUL		R6		R7
		LDR		R7		NEG
		MUL		R6		R7
		LDR		R7		OPDV
		ADD 	R6		R7
		STR 	R6		OPDV
ENF2	MOV		SP		FP			; pop off the stack
		MOV		R0		SP
		ADI		R0		-8
		LDR		R0		R0
		MOV		FP		R0
		MOV		R0		SP
		CMP 	R0		SB
		BGT		R0		UNFLW
		MOV		R0		SP
		ADI		R0		-4
		LDR		R0		R0
		MOV		PC		R0
FLSH	LDR		R7		ZERO 		; ==========================================================================================> Func FLSH (flush)			
		STR 	R7		DATA		; Removed the following lines from top of FLSH: LDR		R7		ZERO \n LDR		R6		FP \n ADI		R6		-12 \n STR 	R7		R6
		LDB		R6		NL 			; zero out data
GNL		TRP		4					; look for new line char (Grab New Line)
		CMP 	R3		R6
		BNZ		R3		GNL
		STB 	R3		C 			; nullify c
		MOV		SP		FP			; pop off the stack
		MOV		R0		SP
		ADI		R0		-8
		LDR		R0		R0
		MOV		FP		R0
		MOV		R0		SP
		CMP 	R0		SB
		BGT		R0		UNFLW
		MOV		R0		SP
		ADI		R0		-4
		LDR		R0		R0
		MOV		PC		R0
GDTA	LDR		R7		CNT 		; ==========================================================================================> Func GDTA (getdata)
		LDR		R6		SIZE
		CMP 	R6		R7
		BRZ		R6		TBIG
		LDA		R5		C
		ADD 	R5		R7
		TRP		4
		STB 	R3		R5
		ADI		R7		1
		STR 	R7		CNT
		JMP		ENGL
TBIG	LDB		R3		N 			; printf("Number too Big\n");
		TRP		3
		LDB		R3		u
		TRP		3
		LDB		R3		m
		TRP		3
		LDB		R3		b
		TRP		3
		LDB		R3		e
		TRP		3
		LDB		R3		r
		TRP		3
		LDB		R3		SPC
		TRP		3
		LDB		R3		t
		TRP		3
		LDB		R3		o
		TRP		3
		LDB		R3		o
		TRP		3
		LDB		R3		SPC
		TRP		3
		LDB		R3		B
		TRP		3
		LDB		R3		i
		TRP		3
		LDB		R3		g
		TRP		3
		LDB		R3		NL
		TRP		3
		MOV		R0		SP 			; begin calling FLSH, check for stack overflow (no parameters)
		ADI		R0 		-8			; FLSH takes no parameters
		CMP		R0		SL
		BLT		R0		OVFLW
		MOV		R0		SP
		MOV		R1		PC
		ADI		R1		96
		ADI 	SP		-4		
		STR		R1		SP			; STORE RETURN ADDRESS
		ADI		SP		-4
		STR 	FP		SP			; STORE NULL VAL FOR PFP
		MOV		FP		SP
		ADI		FP		8
		JMP		FLSH
ENGL 	MOV		SP		FP			; pop off the stack
		MOV		R0		SP
		ADI		R0		-8
		LDR		R0		R0
		MOV		FP		R0
		MOV		R0		SP
		CMP 	R0		SB
		BGT		R0		UNFLW
		MOV		R0		SP
		ADI		R0		-4
		LDR		R0		R0
		MOV		PC		R0
RST		MOV		R7		SP 			; ==========================================================================================> Func RST (reset)
		ADI		R7		-4			; only one local int required
		CMP 	R7		SL
		BLT		R7		OVFLW
		ADI		SP 		-4			; allocate space for local var K
		MOV		R7		FP
		ADI		R7		-28
		LDR		R6		ZERO
		STR 	R6		R7
FOR 	MOV		R7		FP 			; grab k
		ADI		R7		-28
		LDR		R7		R7
		LDR		R6		SIZE
		CMP 	R6		R7 			; compare size and k
		BRZ		R6		EFOR		; branch if size == k
		LDA		R6		C 			; get address of C[k]
		ADD 	R6		R7
		LDB		R5		0	 		; save zero into c[k]
		STB		R5		R6
		ADI		R7		1 			; increment k
		MOV		R6		FP
		ADI		R6		-28
		STR 	R7		R6 			; save k back to memory
		JMP 	FOR
EFOR	MOV		R7		FP			; data = w
		ADI		R7		-12
		LDR		R7		R7
		STR 	R7		DATA
		MOV		R7		FP 			; opdv = x
		ADI		R7		-16
		LDR		R7		R7
		STR 	R7		OPDV
		MOV		R7		FP 			; cnt = y
		ADI		R7		-20
		LDR		R7		R7
		STR 	R7		CNT
		MOV		R7		FP 			; flag = z
		ADI		R7		-24
		LDR		R7		R7
		STR 	R7		FLAG
		MOV		SP		FP			; pop off the stack
		MOV		R0		SP
		ADI		R0		-8
		LDR		R0		R0
		MOV		FP		R0
		MOV		R0		SP
		CMP 	R0		SB
		BGT		R0		UNFLW
		MOV		R0		SP
		ADI		R0		-4
		LDR		R0		R0
		MOV		PC		R0
MAIN	MOV		R0		SP 			; ==========================================================================================> Func MAIN
		ADI		R0 		-14			; RST takes 4 ints
		CMP		R0		SL
		BLT		R0		OVFLW
		MOV		R0		SP
		LDR		R1		ONE 		; w (-12) = 1
		ADI		R0		-12
		STR 	R1		R0
		LDR		R1		ZERO 		; x (-16) = 0
		ADI		R0		-4
		STR 	R1		R0
		ADI		R0		-4 			; y (-20) = 0
		STR 	R1		R0
		ADI		R0		-4 			; z (-24) = 0
		STR 	R1		R0
		MOV		R1		PC
		ADI		R1		96
		ADI 	SP		-4		
		STR		R1		SP			; STORE RETURN ADDRESS
		ADI		SP		-4
		STR 	FP		SP			; STORE NULL VAL FOR PFP
		MOV		FP		SP
		ADI		FP		8
		JMP		RST					; >>>>> Call RST
		MOV		R0		SP 			; begin calling GDTA, check for stack overflow (no parameters)
		ADI		R0 		-8			; GDTA takes no parameters
		CMP		R0		SL
		BLT		R0		OVFLW
		MOV		R0		SP
		MOV		R1		PC
		ADI		R1		108
		ADI 	SP		-4		
		STR		R1		SP			; STORE RETURN ADDRESS
		ADI		SP		-4
		STR 	FP		SP			; STORE NULL VAL FOR PFP
		MOV		FP		SP
		ADI		FP		8
		ADI		SP 		-16
		JMP		GDTA 				; >>>>> Call GDTA
MNWL	LDB		R0		C
		LDB		R1		@
		CMP 	R1		R0 			; check if C[0] == @
		BRZ		R1		FIN 		; if true, end program
		LDB		R1		+			; otherwise begin testing if(C[0] == + || C[0] == -)
		CMP 	R1		R0			; (C[0] == +)? 0 : !0
		BRZ		R1		SIGN
		LDB		R2		-
		CMP 	R2		R0 			; (C[0] == -)? 0 : !0
		BRZ		R2		SIGN
		JMP		LS
SIGN	MOV		R0		SP 			; begin calling GDTA, check for stack overflow (no parameters)
		ADI		R0 		-8			; GDTA takes no parameters
		CMP		R0		SL
		BLT		R0		OVFLW
		MOV		R0		SP
		MOV		R1		PC
		ADI		R1		96
		ADI 	SP		-4		
		STR		R1		SP			; STORE RETURN ADDRESS
		ADI		SP		-4
		STR 	FP		SP			; STORE NULL VAL FOR PFP
		MOV		FP		SP
		ADI		FP		8
		JMP		GDTA 				; >>>>> Call GDTA
		JMP		WDTA
LS		LDA		R7		C 			; load address of C[0]
		LDB		R6		R7			; load value of C[0]
		ADI		R7		1			; load address of C[1]
		STB		R6		R7			; save value of C[0] to C[1]
		ADI		R7		-1			; load address of C[0]
		LDB		R6		+
		STB		R6		R7			; store + in C[0]
		LDR		R7		CNT 		; increment CNT
		ADI		R7		1
		STR 	R7		CNT
WDTA	LDR		R0		DATA 		; while(data)
		BRZ		R0		EWDT
		LDA		R7		C 			; if (c[cnt-1] == '\n')
		LDR		R6		CNT
		ADD 	R7		R6
		ADI		R7		-1
		LDB		R7		R7
		LDB		R5		NL
		CMP 	R5		R7
		BNZ		R5		WDEL
		LDR		R7		ZERO 		; BODY OF IF
		STR 	R7		DATA
		LDR		R7		ONE
		STR 	R7		TENTH
		ADI		R6		-2
		STR 	R6		CNT
WHL		LDR		R6		CNT
		BRZ		R6		FINW
		LDR		R7		FLAG
		BNZ		R7		FINW
		MOV		R0		SP 			; begin calling OPD, check for stack overflow (no parameters) ///////////////////////////////// OPD (opd)
		ADI		R0 		-14			; opd takes a char s, int k, char j
		CMP		R0		SL
		BLT		R0		OVFLW
		MOV		R0		SP
		LDA		R5		C
		LDR		R4		CNT
		ADD 	R5		R4
		LDB		R1		R5
		ADI		R0		-14
		STB 	R1		R0
		LDR		R1		TENTH
		ADI		R0		1
		STR 	R1		R0
		LDA		R5		C
		LDB		R1		R5
		ADI		R0		4
		STB 	R1		R0
		MOV		R1		PC
		ADI		R1		96
		ADI 	SP		-4		
		STR		R1		SP			; STORE RETURN ADDRESS
		ADI		SP		-4
		STR 	FP		SP			; STORE NULL VAL FOR PFP
		MOV		FP		SP
		ADI		FP		8
		JMP		OPD
		LDR		R7		CNT
		ADI		R7		-1
		STR 	R7		CNT
		LDR		R7		TENTH
		LDR		R6		TEN
		MUL		R7		R6
		STR 	R7		TENTH
		JMP		WHL
FINW	LDR		R7		FLAG
		BNZ		R7		WDEL
		LDB		R3		O
		TRP		3
		LDB		R3		p
		TRP		3		
		LDB		R3		e
		TRP		3		
		LDB		R3		r
		TRP		3		
		LDB		R3		a
		TRP		3		
		LDB		R3		n
		TRP		3		
		LDB		R3		d
		TRP		3		
		LDB		R3		SPC
		TRP		3		
		LDB		R3		i
		TRP		3		
		LDB		R3		s
		TRP		3		
		LDB		R3		SPC
		TRP		3		
		LDR		R3		OPDV
		TRP		1		
		LDB		R3		NL
		TRP		3
		JMP		WDTA
WDEL	MOV		R0		SP 			; begin calling GDTA, check for stack overflow (no parameters)
		ADI		R0 		-8			; GDTA takes no parameters
		CMP		R0		SL
		BLT		R0		OVFLW
		MOV		R0		SP
		MOV		R1		PC
		ADI		R1		96
		ADI 	SP		-4		
		STR		R1		SP			; STORE RETURN ADDRESS
		ADI		SP		-4
		STR 	FP		SP			; STORE NULL VAL FOR PFP
		MOV		FP		SP
		ADI		FP		8
		JMP		GDTA
		JMP		WDTA
EWDT	MOV		R0		SP 			; begin calling RST, check for stack overflow
		ADI		R0 		-14			; RST takes 4 ints
		CMP		R0		SL
		BLT		R0		OVFLW
		MOV		R0		SP
		LDR		R1		ONE 		; w (-12) = 1
		ADI		R0		-12
		STR 	R1		R0
		LDR		R1		ZERO 		; x (-16) = 0
		ADI		R0		-4
		STR 	R1		R0
		ADI		R0		-4 			; y (-20) = 0
		STR 	R1		R0
		ADI		R0		-4 			; z (-24) = 0
		STR 	R1		R0
		MOV		R1		PC
		ADI		R1		108
		ADI 	SP		-4		
		STR		R1		SP			; STORE RETURN ADDRESS
		ADI		SP		-4
		STR 	FP		SP			; STORE NULL VAL FOR PFP
		MOV		FP		SP
		ADI		FP		8
		ADI 	SP 		-16
		JMP		RST
		MOV		R0		SP 			; begin calling GDTA, check for stack overflow
		ADI		R0 		-8			; GDTA takes no parameters
		CMP		R0		SL
		BLT		R0		OVFLW
		MOV		R0		SP
		MOV		R1		PC
		ADI		R1		96
		ADI 	SP		-4		
		STR		R1		SP			; STORE RETURN ADDRESS
		ADI		SP		-4
		STR 	FP		SP			; STORE NULL VAL FOR PFP
		MOV		FP		SP
		ADI		FP		8
		JMP		GDTA
		JMP		MNWL
		JMP		FIN 				
FIN		TRP		0
